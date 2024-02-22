package gupsmile.com.data.temporalConfig

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.data.firebaseManager.FireStoreManager
import gupsmile.com.model.Note
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.util.Date
import javax.inject.Inject


enum class StateGetReviews{
    LOADING,
    DONE,
    ERROR,
    UNDEFINE,
    RELOADINGLIST
}

enum class StateChangesOnListReviews{
    NEWCHANGES,
    ACTIVE,
    LOGOUTCURRENTUSER,
    LOGINCURRENTUSER,
    START,
    UNDEFINE

}


enum class StateTypeOfOperation{
    ADD,
    DELETE,
    UPDATE,
    UNDEFINE,
}


enum class StateUpdateListGups{
    LOADING,
    DONE,
    ERROR,
    UNDEFINE,
}

enum class StateNotificationGupAdded{
    ACTIVE,
    NOACTIVE,
    UNDEFINE
}

/**
 * Data class que almacena el estado controlado por ViewModelGetReviews*/
data class  TemporalGetReviewsUiState(
    var state: StateGetReviews = StateGetReviews.UNDEFINE,
    var notes: MutableList<Note> = mutableListOf(),
    val stableNotes: List<Note> = listOf(),
    val stableNotesNew: List<Note> = listOf(),
    var statechanges: StateChangesOnListReviews = StateChangesOnListReviews.ACTIVE,
    var stateUpdateListGups: StateUpdateListGups = StateUpdateListGups.UNDEFINE,
    var stateTypeOfOperation: StateTypeOfOperation = StateTypeOfOperation.UNDEFINE,
    var temporalGup: Note? = null,
    var stateNotificationGupAdded: StateNotificationGupAdded = StateNotificationGupAdded.UNDEFINE
)


/**
 * viewModel encargado del manejo de la sección Gups, recibe actualizaciones de FireStoreManager,
 * @param fireStoreManager objeto de tipo FireStoreManager que se encarga de las solicitudes de red
 * para la actualización de la sección de Gups.
 * @param authManager objeto de tipo AuthManager que se encarge de la autenticación de usuario*/
@HiltViewModel
class ViewModelGetReviews @Inject constructor(
    private val fireStoreManager: FireStoreManager,
    private val authManager: AuthManager
) : ViewModel() {

    /**
     * validamos la autenticación del usuario
     * */
    fun getCurrentUser() = authManager.getCurrentUser()


    /**Gestiona el colector del flujo responsable de la actualización global de la lista de Gups
     * */
    suspend  fun result() = fireStoreManager.getNotesFlow()
        .catch {
            updateStateGetReviewsState(newValue = StateGetReviews.ERROR)
            updateNotes(result = mutableListOf())
            Log.d("FlowDectect", "error detectado en catch del flow: ${it.message}")
        }
        .collect {
            updateStateGetReviewsState(newValue = StateGetReviews.LOADING)
            updateNotes(result = it.toMutableList())
            Log.d("FlowDectect", "Collector activo Get Notes")
        }


    private val _uiState = MutableStateFlow(TemporalGetReviewsUiState())
    val uiState: StateFlow<TemporalGetReviewsUiState> = _uiState

    init {
        updateStateChangesOnListReviews(StateChangesOnListReviews.START)
    }



    /**
     * Gestiona el estado de actualización global de la lista de Gups
     * */
    private fun updateStateGetReviewsState(newValue: StateGetReviews){
        _uiState.update {
            it.copy(
               state = newValue
            )
        }
    }

    /**
     * gestiona la actualización global de la lista de Gups al detectarse operaciones de escritura,
     * agregación o eliminación.
     * */
    fun updateNotes(result: MutableList<Note>){


        var currentStableList: List<Note>

        if( _uiState.value.stableNotes.isEmpty()){
            result.sortByDescending { it.dateNote }
            _uiState.update {
                Log.d("FlowDectect", "lista completa agregada")
                it.copy(
                    notes = result.toMutableList(),
                    stableNotesNew = result.toList(),
                    stableNotes = result.toList()
                )
            }
        }else{
            viewModelScope.launch {
                val currentList = _uiState.value.notes
                val notesToAdd = result.filterNot { it in currentList }
                val notesToRemove = currentList.filterNot { it in result }
                currentList.addAll(notesToAdd)
                currentList.removeAll(notesToRemove)
                currentList.sortByDescending { it.dateNote }
                _uiState.update { currentState ->
                    Log.d("FlowDectect", "elemento agregado individualmente")
                    currentState.copy(
                        notes = currentList
                    )
                }
                currentStableList = currentList.toList()
                if(currentStableList.isNotEmpty()){
                    _uiState.update {
                        it.copy(
                            stableNotes = currentStableList
                        )

                    }
                }
            }
        }
        if(uiState.value.notes.isEmpty()){
            updateStateGetReviewsState(newValue = StateGetReviews.RELOADINGLIST)
        }else{
            updateStateGetReviewsState(newValue = StateGetReviews.DONE)
            updateStateChangesOnListReviews(StateChangesOnListReviews.UNDEFINE)
        }



        if(uiState.value.stateTypeOfOperation == StateTypeOfOperation.UPDATE ||
            uiState.value.stateTypeOfOperation == StateTypeOfOperation.DELETE ||
            uiState.value.stateTypeOfOperation == StateTypeOfOperation.ADD
            ){

            val currentStableList = uiState.value.stableNotes

            if(uiState.value.temporalGup != null){
                val foundNote: Note? = currentStableList.find { it.dateNote == uiState.value.temporalGup!!.dateNote }
                viewModelScope.launch {
                   val validateWrittenInBackenGup =  updateStateGupInBackend(foundNote?.id.toString())
                    if(validateWrittenInBackenGup){
                        updateStateNotificationGupAdded(StateNotificationGupAdded.ACTIVE)
                    }
                }
                Log.d("FlowDectect", "trazabilidad de código en validación de nuevo gup en el backend")
            }
            updateStateTypeOfOperation(StateTypeOfOperation.UNDEFINE)
        }

        updateStateListGups(StateUpdateListGups.DONE)

    }


    /**
     * Actualiza el estado de cambios en la lista de Gups,
     * */
    fun updateStateChangesOnListReviews(newValue: StateChangesOnListReviews){
        _uiState.update {
            it.copy(
                statechanges = newValue
            )
        }
    }

    /**Actializa el estado global de la lista de gups al momento de hacer cambios (agregaciones,
     * actualizaciones o eliminaciones de elementos de la lista.)*/
    fun updateStateListGups(newValue: StateUpdateListGups){
        _uiState.update {
            it.copy(
                stateUpdateListGups = newValue
            )
        }
    }


    /**
     * actualiza el estado para el tipo de operación a realizar en la lista de Gups
     * */
    fun updateStateTypeOfOperation(newValue: StateTypeOfOperation){
        _uiState.update {
            it.copy(
                stateTypeOfOperation = newValue
            )
        }
    }


    /**Actualiza el el Gups que se almacenará de forma temporal a efectos de actualizaciones,
     * eliminaciones o validaciones en el backend
     * */
    fun updateTemporalGup(newValue: Note){
        _uiState.update {
            it.copy(
                temporalGup =  newValue
            )
        }
    }

    /**
     * Actualiza el estado de validación de nuevo Gup cargado al backend
     * */
    fun updateStateNotificationGupAdded(newValue: StateNotificationGupAdded){
        _uiState.update {
            it.copy(
                stateNotificationGupAdded = newValue
            )
        }
    }

    /**
     * Método que gestiona la eliminación de un Gup
     * */
    suspend fun deleteNote(noteId: String){
        updateStateTypeOfOperation(StateTypeOfOperation.DELETE)
        updateStateListGups(StateUpdateListGups.LOADING)
        fireStoreManager.deleteNote(noteId = noteId)
    }

    /**Método que gestiona la agregación de un nuevo Gup
     * */
    suspend fun addNote(note: Note){
        updateStateTypeOfOperation(StateTypeOfOperation.ADD)
        updateStateListGups(StateUpdateListGups.LOADING)
        fireStoreManager.addNote(note = note)
    }



    /**Método que gestiona la actualización de un Gup en la lista*/
    suspend fun updateNote(note: Note, noteId: String){
        updateStateTypeOfOperation(StateTypeOfOperation.UPDATE)
        updateStateListGups(StateUpdateListGups.LOADING)
        fireStoreManager.updateNote(note = note, noteId = noteId)
    }


    /**
     * Método que valida la escritura de un gup en el backend después de una operación de escritura,
     * actualización o eliminación, admite como  parámetro la id del gup.
     * */
    suspend fun updateStateGupInBackend(noteId: String) = fireStoreManager.updateStateGupInBackend(noteId)
}
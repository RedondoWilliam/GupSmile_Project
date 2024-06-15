package gupsmile.com.data.temporalConfig

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.data.firebaseManager.FireStoreManager
import gupsmile.com.data.firebaseManager.VerifyQueryAddNewGup
import gupsmile.com.model.Note
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import org.jetbrains.annotations.Async
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


/**
 * Estados sobre el tipo de operación en la lista de gups*/
enum class StateTypeOfOperation{
    ADD,
    DELETE,
    UPDATE,
    UNDEFINE,
}


/**
 * Estados de acutalización de la lista de Gups.
 * Controla directamente el estado de carga en la UI
 * */
enum class StateUpdateListGups{
    LOADING,
    DONE,
    ERROR,
    UNDEFINE,
    VERIFYAGAIN
}

enum class StateNotificationGupAdded{
    SUCCESS,
    FAILED,
    UNDEFINE
}

enum class StateQueryGupsList{
    ACTIVE,
    INACTIVE,
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
    var stateNotificationGupAdded: StateNotificationGupAdded = StateNotificationGupAdded.UNDEFINE,
    var stateQueryGupsList: StateQueryGupsList = StateQueryGupsList.UNDEFINE,
    var numberOfTriesAddNewGup: Int = 0,
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
    suspend  fun result() {
        fireStoreManager.getNotesFlow()
            .catch {
                updateStateGetReviewsState(newValue = StateGetReviews.ERROR)
                updateNotes(result = mutableListOf())
                Log.d("FlowDectect", "error detectado en catch del flow: ${it.message}")
            }
            .collect {
                updateStateGetReviewsState(newValue = StateGetReviews.LOADING)
                updateNotes(result = it.toMutableList())
                Log.d("FlowDectect", "Collector activo Get Notes, flujo de datos completo")
            }

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
        if(uiState.value.notes.isEmpty()){
            updateStateGetReviewsState(newValue = StateGetReviews.RELOADINGLIST)
            Log.d("FlowDectect", "lista de Gups vacía ")
        }else{
            updateStateGetReviewsState(newValue = StateGetReviews.DONE)
            updateStateChangesOnListReviews(StateChangesOnListReviews.UNDEFINE)
            updateStateListGups(StateUpdateListGups.DONE)
            Log.d("FlowDectect", "lista de Gups NO vacía")
        }
    }

    suspend fun validateWritingInBackend(){
        QueryProtocolUseCase {
           if(
                uiState.value.stateTypeOfOperation == StateTypeOfOperation.ADD
            ) {

                val currentStableList = uiState.value.stableNotes

                if(uiState.value.temporalGup != null){
                    Log.d("FlowDectect", "ejecución de función validateWritingInBackend")
                    val foundNote: Note? = currentStableList.find { it.dateNote == uiState.value.temporalGup!!.dateNote }
                    val result = viewModelScope.launch {
                        updateStateGupInBackend(foundNote?.id.toString()).collect{
                            when(it){
                                VerifyQueryAddNewGup.SUCCESS ->{
                                    updateStateListGups(StateUpdateListGups.UNDEFINE)
                                    updateStateNotificationGupAdded(StateNotificationGupAdded.SUCCESS)
                                    updateStateTypeOfOperation(StateTypeOfOperation.UNDEFINE)
                                    Log.d("FlowDectect", "Validación de Gup Success en el ViewModel")
                                    foundNote?.backendState = false
                                }
                                VerifyQueryAddNewGup.FAILDED ->{
                                    if(uiState.value.numberOfTriesAddNewGup <= 5){
                                        updateNumberOfTriesAddNewGup(uiState.value.numberOfTriesAddNewGup + 1)
                                        updateStateListGups(StateUpdateListGups.VERIFYAGAIN)
                                    }
                                    if(uiState.value.numberOfTriesAddNewGup > 5){
                                        updateNumberOfTriesAddNewGup(0)
                                        updateStateNotificationGupAdded(StateNotificationGupAdded.FAILED)
                                        updateStateTypeOfOperation(StateTypeOfOperation.UNDEFINE)
                                        updateStateListGups(StateUpdateListGups.UNDEFINE)
                                        Log.d("FlowDectect", "Validación de Gup Failed en el ViewModel")
                                        foundNote?.backendState = true
                                    }
                                }
                                VerifyQueryAddNewGup.ERROR->{
                                    updateStateNotificationGupAdded(StateNotificationGupAdded.FAILED)
                                    updateStateTypeOfOperation(StateTypeOfOperation.UNDEFINE)
                                    updateStateListGups(StateUpdateListGups.UNDEFINE)
                                    Log.d("FlowDectect", "Error en la Validación de Nuevo Gup en el ViewModel")
                                }
                            }
                        }
                    }
                    viewModelScope.launch {
                        result.join()
                        currentStableList.toList()
                        if(currentStableList.isNotEmpty()){
                            _uiState.update {
                                it.copy(
                                    stableNotes = currentStableList
                                )

                            }
                        }
                        Log.d("FlowDectect", "trazabilidad de código en validación de nuevo gup en el backend")
                    }

                }
            }
        }
    }

    fun updateNumberOfTriesAddNewGup(newValue: Int){
        _uiState.update {
            it.copy(
                numberOfTriesAddNewGup = newValue
            )
        }
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
     * Actualiza el estado de validación de nuevo Gup cargado al backend a efectos de actualizar
     * la notificación de nuevo gup cargado en la UI
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
        Log.d("FlowDetect", "Ejecución de Función de eliminación de  Gup")
    }

    /**Método que gestiona la agregación de un nuevo Gup
     * */
    suspend fun addNote(note: Note){
        viewModelScope.launch {
            QueryProtocolUseCase {
                try {
                    updateStateListGups(StateUpdateListGups.LOADING)
                    updateStateTypeOfOperation(StateTypeOfOperation.ADD)
                    launch {
                        fireStoreManager.addNote(note = note)
                    }
                    Log.d("FlowDetect", "Ejecución de Función de Agregación de nuevo Gup")
                }catch (d: CancellationException){
                    Log.d("FlowDectect", "error en actualización de Nuevo Gup: ${d.message}")
                }
            }
        }
    }



    /**Método que gestiona la actualización de un Gup en la lista*/
    suspend fun updateNote(note: Note, noteId: String){
        updateStateTypeOfOperation(StateTypeOfOperation.UPDATE)
        updateStateListGups(StateUpdateListGups.LOADING)
        fireStoreManager.updateNote(note = note, noteId = noteId)
        Log.d("FlowDetect", "Ejecución de Función de Actualización  de  Gup")
    }


    /**
     * Método que valida la escritura de un gup en el backend después de una operación de escritura,
     * actualización o eliminación, admite como  parámetro la id del gup.
     * */
    suspend fun updateStateGupInBackend(noteId: String) = fireStoreManager.updateStateGupInBackend(noteId)
}

suspend fun QueryProtocolUseCase(actions: () -> Unit) = coroutineScope{
    val timeLimite = launch {
        delay(5000)
    }
    val result = launch {
        actions()
    }
    val asyncTimeLimite = async {
        timeLimite.join()
        if(result.isActive){
            result.cancel()
        }
    }
    val asyncResult = async {
        result.join()
        if(timeLimite.isActive){
            timeLimite.cancel()
        }
    }
    val jobi = launch {
        asyncTimeLimite.await()
        asyncResult.await()
    }
    jobi.join()
}

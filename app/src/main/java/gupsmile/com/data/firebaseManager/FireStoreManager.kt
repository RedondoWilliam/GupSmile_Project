package gupsmile.com.data.firebaseManager

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.compose.runtime.rememberUpdatedState
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.MetadataChanges
import gupsmile.com.data.firebaseManager.PendingWrittingStatus.UNDEFINE
import gupsmile.com.data.firebaseService.FirebaseStoreService
import gupsmile.com.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.Flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FireStoreManager @Inject constructor(
    private  val context: Context,
    private val authManager: AuthManager,
    private val fireStore: FirebaseFirestore
):FirebaseStoreService {

    private var counter: Int = 0
    private  var  temporalNoteId: String = ""
    private var writeGupInBackend = UNDEFINE

    override fun userId() = authManager.getCurrentUser()?.uid


    /**
     * recibe como parámetro un onbjeto de tipo note, llamamos a una colección llamada note, si no
     * existe la va a crear,*/
    override suspend fun addNote(note: Note){
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val dateTime = dateFormat.format(calendar.time)
        val date = dateFormat.parse(dateTime)
        note.dateNote = date
        note.userId = userId().toString()
        Log.d("FlowDectect", "Ejecución de Función de Agregación de nuevo Gup En la capa de datos")
        fireStore.collection("notes").add(note).await()
    }

    /**
     * actualizamos la nota utilizando el id de Note para instanciarlo*/
    override suspend fun updateNote( note: Note, noteId: String){
        note.userId = userId().toString()
        val noteFre = fireStore.collection("notes").document(noteId)
        noteFre.set(note)?.await()

//
//        /**
//         * obtenemos una referencia de la nota a través de su identificador*/
//        val noteRef = note.id?.let {
//            fireStore.collection("notes").document(it)
//        }
//        /**actualizamos la nota*/
//        noteRef?.set(note)?.await()
    }

    /**
     * para eleminar una nota creamos una referencia a esa nota através de su id y la eliminamos*/
    override suspend fun deleteNote(noteId: String){
        val noteRef = fireStore.collection("notes").document(noteId)
        noteRef.delete().await()
    }

    /**
     * Un flujo me devuelve un flujo continuo de datos que podemos producir, transformar y consumir
     * de manera asíncrona*/
//    override fun getNotesFlow() = callbackFlow{
//
//            val subscription = fireStore.collection("notes")
//                .whereEqualTo("userId", userId())
//                .orderBy("content")
//                .addSnapshotListener(MetadataChanges.INCLUDE) { snapShot, e ->
//
//                    if(snapShot == null){
//                        return@addSnapshotListener
//                        Log.d("FlowDectect", " Error en ejecución de consulta")
//                    }
//
//                    if(e != null){
//                        Log.d("FlowDectect", "Error en el addSnapShotListener: ${e.message} ")
//                        return@addSnapshotListener
//                    }
//
//
//                    if(snapShot != null ){
//
//                        if(snapShot.metadata.hasPendingWrites()){
//                            Log.d("FlowDectect", "tenemos  cambios locales que aún no se han " +
//                                    "escrito en el backend")
//                        }else{
//                            Log.d("FlowDectect", " NO tenemos  cambios locales que aún no se han " +
//                                    "escrito en el backend")
//                        }
//
//                        snapShot?.let { querySnapShot ->
//                            val notes = mutableListOf<Note>()
//                            for (document in querySnapShot.documents){
//                                val note = document.toObject(Note::class.java)
//                                note?.id = document.id
//                                note?.let { notes.add(it)  }
//                            }
//                            trySend(notes).isSuccess
//                            Log.d("FlowDectect", "snapshot activo")
//                        }
//                        Log.d("FlowDectect", "Productor activo Get Notes")
//                    }else{
//                        Log.d("FlowDectect", "snapshot nulo")
//                    }
//
//                    for(dc in snapShot!!.documentChanges){
//                        when(dc.type){
//                            DocumentChange.Type.ADDED -> Log.d("FlowDectect", "elemento agregado ${dc.document.data}")
//                            DocumentChange.Type.MODIFIED -> Log.d("FlowDectect", "elemento modificado: ${dc.document.data}")
//                            DocumentChange.Type.REMOVED -> Log.d("FlowDectect", "elemento eliminado: ${dc.document.data}")
//                        }
//                    }
//                }
//            awaitClose{subscription?.remove()}

//        }




     override fun getNotesFlow() = flow<List<Note>>{

        Log.d("FlowDectect", "query ACTIVE GupsList")

         val notes = mutableListOf<Note>()
         fireStore.collection("notes")
             .whereEqualTo("userId", userId())
             .get()
             .addOnSuccessListener { snapShot ->

                 snapShot?.let { querySnapShot ->
                     for (document in querySnapShot.documents){
                         val note = document.toObject(Note::class.java)
                         note?.id = document.id
                         note?.backendState = document.metadata.hasPendingWrites()
                         note?.let { notes.add(it)}
                     }
                     Log.d("FlowDectect", "snapshot activo")
//                     for(dc in querySnapShot.documentChanges){
//                         when(dc.type){
//                            DocumentChange.Type.ADDED -> Log.d("FlowDectect", "elemento agregado ${dc.document.data}")
//                            DocumentChange.Type.MODIFIED -> Log.d("FlowDectect", "elemento modificado: ${dc.document.data}")
//                            DocumentChange.Type.REMOVED -> Log.d("FlowDectect", "elemento eliminado: ${dc.document.data}")
//                        }
//                     }
                 }
                 if(snapShot.metadata.hasPendingWrites()){

                     Log.d("FlowDectect", "tenemos  cambios locales que aún no se han " +
                             "escrito en el backend")


                 }else{
                     Log.d("FlowDectect", " NO tenemos  cambios locales que aún no se han " +
                             "escrito en el backend")
                 }

             }
             .addOnFailureListener { exception ->
                 Log.d("FlowDectect", "Error en el addSnapShotListener: ${exception.message} ")
             }
             .await()
         emit(notes)

        Log.d("FlowDectect", "query INACTIVE GupsList")

    }


//    suspend fun validateGupSnapshotresult():VerifyQueryAddNewGup{
//        return when (writeGupInBackend) {
//
//            PendingWrittingStatus.FAILED -> {
//                Log.d("FlowDectect", " el nuevo gup aún no se ha escrito en el backend")
//                counter += 1
//                if (counter <= 10 && temporalNoteId != "") {
//                    GlobalScope.launch {
//                        delay(200)
//                    }
//                    updateStateGupInBackend(temporalNoteId)
//                    Log.d("FlowDectect", " reintentando el envío.")
//                    writeGupInBackend = UNDEFINE
//                    VerifyQueryAddNewGup.TRYING
//                } else {
//                    counter == 0
//                    temporalNoteId == ""
//                    Log.d("FlowDectect", "fallo completo en el envío.")
//                    writeGupInBackend = UNDEFINE
//                    VerifyQueryAddNewGup.FAILDED
//                }
//            }
//
//            PendingWrittingStatus.SUCCESS -> {
//                Log.d("FlowDectect", " el nuevo gup SI se ha escrito en el backend")
//                counter == 0
//                temporalNoteId == ""
//                writeGupInBackend = UNDEFINE
//                VerifyQueryAddNewGup.SUCCESS
//            }
//
//            UNDEFINE -> {
//                Log.d(
//                    "FlowDectect",
//                    " problemas en la implementación de validación en el backend de nuevo gup"
//                )
//                counter == 0
//                temporalNoteId == ""
//                writeGupInBackend = UNDEFINE
//                VerifyQueryAddNewGup.UNDEFINE
//
//            }
//
//        }
//    }


    /**Valida la escritura del nuevo gup en el backend*/
    override suspend  fun updateStateGupInBackend(noteId: String)= callbackFlow{
        val noteRef = fireStore
            .collection("notes")
            .document(noteId)
        noteRef
            .get()
            .addOnCompleteListener { snapShot ->
                if (snapShot.isSuccessful) {
                    val document = snapShot.result
                    if (document != null) {
                        Log.d(
                            "FlowDectect",
                            " ejecución del snapshot de validación de gup activo"
                        )
                        if (!document.metadata.hasPendingWrites()) {
                            Log.d(
                                "FlowDectect",
                                " resultado de la validación : ${document.metadata.hasPendingWrites()}. " +
                                        "NO HAY elementos no cargados, "
                            )
                            trySend(VerifyQueryAddNewGup.SUCCESS)
                        } else {
                            Log.d(
                                "FlowDectect",
                                " resultado de la validación ${document.metadata.hasPendingWrites()} " +
                                        "HAY elementos no cargados, "
                            )
                            trySend(VerifyQueryAddNewGup.FAILDED)
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(
                    "FlowDectect", "Error en el addSnapShotListener de validación en" +
                            "de escritura de Gup en el backend: ${exception.message} "
                )
                trySend(VerifyQueryAddNewGup.ERROR)
            }
        awaitClose {
            Log.d("FlowDectect", "callback Flow cerrado ")
        }
    }
}

enum class VerifyQueryAddNewGup{
    SUCCESS,
    FAILDED,
    ERROR,
}

enum class PendingWrittingStatus{
    SUCCESS,
    FAILED,
    UNDEFINE
}

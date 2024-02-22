package gupsmile.com.data.firebaseManager

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.util.Log
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.MetadataChanges
import gupsmile.com.data.firebaseService.FirebaseStoreService
import gupsmile.com.model.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.Flow
import javax.inject.Inject

class FireStoreManager @Inject constructor(
    private  val context: Context,
    private val authManager: AuthManager,
    private val fireStore: FirebaseFirestore
):FirebaseStoreService {


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
        fireStore.collection("notes").add(note).await()
    }

    /**
     * actualizamos la nota utilizando el id de Note para instanciarlo*/
    override suspend fun updateNote( note: Note, noteId: String){
        note.userId = userId().toString()
        val noteFre = fireStore.collection("notes").document(noteId)
        noteFre?.set(note)?.await()

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

         val notes = mutableListOf<Note>()

         fireStore.collection("notes")
             .whereEqualTo("userId", userId())
             .get()
             .addOnSuccessListener { snapShot ->

                 snapShot?.let { querySnapShot ->
                     for (document in querySnapShot.documents){
                         val note = document.toObject(Note::class.java)
                         note?.id = document.id
                         note?.let { notes.add(it)}
                     }
                     Log.d("FlowDectect", "snapshot activo")
                     for(dc in querySnapShot.documentChanges){
                         when(dc.type){
                            DocumentChange.Type.ADDED -> Log.d("FlowDectect", "elemento agregado ${dc.document.data}")
                            DocumentChange.Type.MODIFIED -> Log.d("FlowDectect", "elemento modificado: ${dc.document.data}")
                            DocumentChange.Type.REMOVED -> Log.d("FlowDectect", "elemento eliminado: ${dc.document.data}")
                        }
                     }
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

    }



    override suspend  fun updateStateGupInBackend(noteId: String): Boolean {
        var writeGupInBackend = false
        val noteRef = fireStore.collection("notes").document(noteId)
        noteRef.get()
            .addOnCompleteListener{snapShot ->
            if(snapShot.isSuccessful){
                val document = snapShot.result
                if(document != null){
                    writeGupInBackend = !document.metadata.hasPendingWrites()
                }
            }
            }
            .addOnFailureListener { exception ->
                Log.d("FlowDectect", "Error en el addSnapShotListener de validación en" +
                        "de escritura de Gup en el backend: ${exception.message} ")
            }
            .await()
        return writeGupInBackend
    }


}
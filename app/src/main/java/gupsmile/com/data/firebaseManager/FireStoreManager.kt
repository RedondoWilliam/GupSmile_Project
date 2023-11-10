package gupsmile.com.data.firebaseManager

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import gupsmile.com.model.Note
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.Flow
import javax.inject.Inject

class FireStoreManager @Inject constructor(
    private  val context: Context,
    private val authManager: AuthManager
) {
    private val fireStore = FirebaseFirestore.getInstance()

    var userId = authManager.getCurrentUser()?.uid

    /**
     * recibe como parámetro un onbjeto de tipo note, llamamos a una colección llamada note, si no
     * existe la va a crear,*/
    suspend fun addNote(note: Note){
        note.userId = userId.toString()
        fireStore.collection("notes").add(note).await()
    }

    /**
     * actualizamos la nota utilizando el id de Note para instanciarlo*/
    suspend fun updateNote(note: Note){
        /**
         * obtenemos una referencia de la nota a través de su identificador*/
        val noteRef = note.id?.let { fireStore.collection("notes").document(it) }
        /**actualizamos la nota*/
        noteRef?.set(note)?.await()
    }

    /**
     * para eleminar una nota creamos una referencia a esa nota através de su id y la eliminamos*/
    suspend fun deleteNote(noteId: String){
        val noteRef = fireStore.collection("notes").document(noteId)
        noteRef.delete().await()
    }

    /**
     * Un flujo me devuelve un flujo continuo de datos que podemos producir, transformar y consumir
     * de manera asíncrona*/
    fun getNotesFlow() = callbackFlow {
        /**
         * obtenemos una referencia a la lista de objetos note filtrada por el userId de usuario*/
        val notesRef = fireStore.collection("notes")
            .whereEqualTo("userId", userId).orderBy("title")


        /**
         * con el snapshot iteramos sobre los datos de tal forma que nos permita construir una lista
         * de datos de tipo Note*/
        val subscription = notesRef.addSnapshotListener {snapshot, _ ->
            snapshot?.let { querySnapshot ->
                val notes = mutableListOf<Note>()
                for (document in querySnapshot.documents){
                    val note = document.toObject(Note::class.java)
                    note?.id = document.id
                    note?.let { notes.add(it) }
                }
                /**enviamos la lista a través del canal llamadao callbackFlow*/
                trySend(notes).isSuccess
            }
        }
        /**eliminamos el listener para evitar fugas de memoria*/
        awaitClose{subscription.remove() }
    }


}
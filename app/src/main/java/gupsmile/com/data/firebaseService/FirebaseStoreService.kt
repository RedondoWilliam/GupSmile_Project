package gupsmile.com.data.firebaseService

import gupsmile.com.model.Note
import kotlinx.coroutines.flow.Flow

interface FirebaseStoreService {
    fun userId() : String?
    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note,  noteId: String)
    suspend fun deleteNote(noteId: String)
    fun getNotesFlow():Flow<List<Note>>

    suspend  fun updateStateGupInBackend(noteId: String):Boolean
}
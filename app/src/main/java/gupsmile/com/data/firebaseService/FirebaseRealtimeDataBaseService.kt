package gupsmile.com.data.firebaseService

import com.google.firebase.database.DatabaseReference
import gupsmile.com.model.Contact
import kotlinx.coroutines.flow.Flow

interface FirebaseRealtimeDataBaseService {
    val dataBaseReference: DatabaseReference
    fun addContact(contact: Contact)
    fun deleteContact(contactId:String)
    fun updateContact(contacId: String, updatedContact: Contact)
    fun showContact(contactId: String): Flow<Contact>
    fun getContactsFlow(): Flow<List<Contact>>
}
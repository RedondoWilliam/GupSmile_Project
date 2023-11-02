package gupsmile.com.data.firebaseManager

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
//import android.system.Os.close
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import gupsmile.com.model.Contact
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RealTimeManager @Inject constructor(
    private val context: Context,
    private val authManager: AuthManager
) {

    /**
     *  necesitamos inicializar una instancia de la base de datos y unirnos a una rama si es que existe,
     *  con reference creamos una referencia en alguna parte de la base de datos, en nuestro caso nos
     *  estamos conectando a un nodo o child llamado contactos, que si no existe lo va a crear
     *  */


    private val dataBaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("contacts")

    fun addContact(contact: Contact){
        /**con key creamos in identificador único para cada objeto contaacto que se crea*/
        val key = dataBaseReference.push().key
        if(key != null){
            /**agregamos un nuevo objeto al child con clave key y valor contact*/
            dataBaseReference.child(key).setValue(contact)
        }
    }

    /**la función para eliminar un contacto recibe un contactId que es el identificador de un
     * contacto en específico*/
    fun deleteContact(contactId:String){
        dataBaseReference.child(contactId).removeValue()
    }

    /**
     *Método de actualización del objeto contacto en la base de datos
     * */
    fun updateContact(contacId: String, updatedContact: Contact){
        dataBaseReference.child(contacId).setValue(updatedContact)
    }

    fun showContact(contactId: String): Flow<Contact> {
        val contact: DatabaseReference = dataBaseReference.child(contactId)

        val flow = callbackFlow<Contact> {
            val listener = contact.addValueEventListener(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val name = snapshot.child("name").getValue(String::class.java)
                        val email = snapshot.child("email").getValue(String::class.java)
                        val address = snapshot.child("address").getValue(String::class.java)
                        val lastName = snapshot.child("lastName").getValue(String::class.java)
                        val phoneNumber = snapshot.child("phoneNumber").getValue(String::class.java)
                        trySend(Contact(
                            name = name.toString(),
                            email = email.toString(),
                            address = address.toString(),
                            phoneNumber = phoneNumber.toString(),
                            lastName = lastName.toString()
                        ))
                    }

                    override fun onCancelled(error: DatabaseError) {
                        /**
                         * En caso de haber error se cierra el canal con el error correspondiente
                         * */
                        Log.w(TAG, "Failed to read value.", error.toException())
                        close(error.toException())
                    }

                })
            awaitClose { contact.removeEventListener(listener) }
        }

        return flow
    }

    /**
     * Método de actualización de lista de contactos mediante Flows
     * */
    fun getContactsFlow(): Flow<List<Contact>>{
        /**
         * solo se va a actualizar la lista de contactos asociados a la cuenta del usuario, por
         * lo que obtenemos una referencia al uid del usuario que se encuentra con sesión iniciada
         * */
        val idFilter = authManager.getCurrentUser()?.uid

        /**
         * creamos nuestro flujo de datas mediante un Callback Flow que va estar constantemente
         * actualizando eventos
         * */
        val flow = callbackFlow {
            /***
             * necesitamos construir un oyente con la función addValueEventListener que obliga a
             * sobreescribir varias funciones,
             * con el listener podemos recibir actualizaciones inmediatas si es que hay actualizaciones
             * en la base de datos, y se va a reflejar en todos los usarios que estén activos en la
             * app
             */
            val listener = dataBaseReference.addValueEventListener(object : ValueEventListener{
                /**
                 * un snapshot es una captura de los datas en ese momento
                 */
                override fun onDataChange(snapshot: DataSnapshot) {
                    /**Convertimos a una lista contacts,
                     * también de cada objeto obtenemos su key*/
                   val contacts = snapshot.children.mapNotNull { snapshot ->
                       val contact = snapshot.getValue(Contact::class.java)
                       snapshot.key?.let { contact?.copy(key = it) }
                   }
                    /**Enviamos y filtramos por uid de usuario*/
                    trySend(contacts.filter { it.uid == idFilter }).isSuccess
                }

                override fun onCancelled(error: DatabaseError) {
                    /**
                     * En caso de haber error se cierra el canal con el error correspondiente
                     * */
                    close(error.toException())
                }
            })
            /**
             * Al cerrar el flow se elimina el oyente para evitar fugas de datos*/
            awaitClose { dataBaseReference.removeEventListener(listener) }
        }
        return flow
    }
}
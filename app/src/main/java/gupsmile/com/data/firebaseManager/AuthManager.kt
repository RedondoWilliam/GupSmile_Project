package gupsmile.com.data.firebaseManager


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import gupsmile.com.model.AuthRes
//import gupsmile.com.network.FirebaseAuthentication
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

//necesitamos contener los datos de autenticación, usamos una sealed class



class AuthManager @Inject constructor(
//    private val auth: FirebaseAuth
)  {

    private val auth: FirebaseAuth by lazy {Firebase.auth}

    /**
     * necesitamos hacer una petición de forma asíncrona para la autenticación de un usuario
     * FirebaseUser va a contener información del ususario que se vaya a a utenticar, en cualquiera
     * de las forma de autenticación
     * */
    suspend fun signInAnonymously(): AuthRes<FirebaseUser> {
        return try {
            val result = auth.signInAnonymously().await()
            AuthRes.Succes(result.user ?: throw Exception("Error al iniciar sesión"))
        } catch (e : Exception){
            AuthRes.Error(e.message ?: "Error al iniciar sesión")
        }
    }


    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): AuthRes<FirebaseUser?>{
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            AuthRes.Succes(authResult.user)
        }catch (e: Exception){
            AuthRes.Error(e.message ?: "Error al crear usuario")
        }
    }


    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthRes<FirebaseUser?>{
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            AuthRes.Succes(authResult.user)
        }catch (e: Exception){
            AuthRes.Error(e.message ?: "Error al iniciar sesión")
        }
    }

    suspend fun resetPassword(email: String):AuthRes<Unit>{
        return try {
            auth.sendPasswordResetEmail(email).await()
            AuthRes.Succes(Unit)
        }catch (e: Exception){
            AuthRes.Error(e.message ?: "Error al reestablecer la contraseña")
        }
    }

    fun signOut(){
        auth.signOut()
    }

    fun getCurrentUser(): FirebaseUser?{
        return auth.currentUser
    }
}
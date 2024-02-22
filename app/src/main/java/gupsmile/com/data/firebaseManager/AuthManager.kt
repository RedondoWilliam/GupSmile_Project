package gupsmile.com.data.firebaseManager


import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import gupsmile.com.R
import gupsmile.com.data.firebaseService.FirebaseAuthenticationService
import gupsmile.com.model.AuthRes
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

//necesitamos contener los datos de autenticación, usamos una sealed class

class AuthManager @Inject constructor(
    private val context: Context,
    private val auth: FirebaseAuth
): FirebaseAuthenticationService {
//    private val auth: FirebaseAuth by lazy {Firebase.auth}

    override val signInClient = Identity.getSignInClient(context)

    /**
     * necesitamos hacer una petición de forma asíncrona para la autenticación de un usuario
     * FirebaseUser va a contener información del ususario que se vaya a a utenticar, en cualquiera
     * de las forma de autenticación
     * */
    override  suspend fun signInAnonymously(): AuthRes<FirebaseUser> {
        return try {
            val result = auth.signInAnonymously().await()
            AuthRes.Succes(result.user ?: throw Exception("Error al iniciar sesión"))
        } catch (e : Exception){
            AuthRes.Error(e.message ?: "Error al iniciar sesión")
        }
    }


    override  suspend fun createUserWithEmailAndPassword(
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


    override  suspend fun signInWithEmailAndPassword(
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

    override  suspend fun resetPassword(email: String):AuthRes<Unit>{
        return try {
            auth.sendPasswordResetEmail(email).await()
            AuthRes.Succes(Unit)
        }catch (e: Exception){
            AuthRes.Error(e.message ?: "Error al reestablecer la contraseña")
        }
    }

    override  fun signOut(){
        auth.signOut()
        signInClient.signOut()

    }

    override  fun getCurrentUser(): FirebaseUser? {
         return auth.currentUser
    }


    override  val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }
    override  fun handleSignInResult(task: Task<GoogleSignInAccount>):AuthRes<GoogleSignInAccount>?{
        return try {
            val account = task.getResult(ApiException::class.java)
            AuthRes.Succes(account)
        }catch (e: ApiException){
            AuthRes.Error(e.message ?: "Google sign-in failed")
        }
    }

    override suspend fun signInWithGoogleCredential(credential: AuthCredential):AuthRes<FirebaseUser>?{
        return try {
            val firebaseUser = auth.signInWithCredential(credential).await()
            firebaseUser.user?.let {
                AuthRes.Succes(it)
            } ?: throw Exception("Sign in with Google failed.")
        }catch (e: Exception){
            AuthRes.Error(e.message ?: "Sign in with Google failed")
        }
    }

    override  fun signInwithGoogle(googleSignInLauncher: ActivityResultLauncher<Intent>){
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }


}
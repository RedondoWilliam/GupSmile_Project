package gupsmile.com.data.firebaseService

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.Api.Client
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import gupsmile.com.model.AuthRes

interface FirebaseAuthenticationService {

    val signInClient: SignInClient
    suspend fun signInAnonymously(): AuthRes<FirebaseUser>
    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): AuthRes<FirebaseUser?>
    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthRes<FirebaseUser?>
    suspend fun resetPassword(email: String):AuthRes<Unit>
    fun signOut()
    fun getCurrentUser(): FirebaseUser?

    val googleSignInClient: GoogleSignInClient
    fun handleSignInResult(task: Task<GoogleSignInAccount>):AuthRes<GoogleSignInAccount>?
    suspend fun signInWithGoogleCredential(credential: AuthCredential):AuthRes<FirebaseUser>?
    fun signInwithGoogle(googleSignInLauncher: ActivityResultLauncher<Intent>)
}
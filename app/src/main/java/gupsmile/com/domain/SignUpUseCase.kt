package gupsmile.com.domain

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.analytics.FirebaseAnalytics
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.model.AuthRes


private suspend fun signUp(
    email: String,
    password: String,
    auth: AuthManager,
    analytics: AnalitycsManager,
    context: Context,
    navigation: NavHostController,
    stateAuthentication: Boolean,
    showDialog: Boolean
){

    if(email.isNotEmpty() && password.isNotEmpty()){
        when(val result = auth.createUserWithEmailAndPassword(email, password)){
            is AuthRes.Succes -> {
                analytics.logButtonClicked(FirebaseAnalytics.Event.SIGN_UP)
                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                stateAuthentication ==!stateAuthentication
                showDialog == !showDialog
            }
            is AuthRes.Error -> {
                analytics.logButtonClicked("Error SignUP: ${result.errorMessage}")
                Toast.makeText(context, "Error SignUp: ${result.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }else{
        Toast.makeText(context, "Existen campos vacíos", Toast.LENGTH_SHORT).show()
    }
}
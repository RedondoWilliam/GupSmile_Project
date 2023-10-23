package gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginAsVisitorSn

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.model.AuthRes
//import gupsmile.com.firebaseManager.AuthRes
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateCurrentUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication
import kotlinx.coroutines.launch

@Composable
fun LoginAsVisitorPanelControl(
    modifier: Modifier = Modifier,
    auth: AuthManager?,
    analytics: AnalitycsManager?,
    navController: NavHostController,
    viewModelAuthentication: ViewModelAuthentication?
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LoginAsVisitorSection(
        bottonActionsLoginAsVisitor = {
           scope.launch {
               incognitSignIn(auth, analytics, context, navController, viewModelAuthentication)
           }
        }
    )
}


private suspend fun incognitSignIn(
    auth: AuthManager?,
    analytics: AnalitycsManager?,
    context: Context,
    navigation : NavHostController,
    viewModelAuthentication: ViewModelAuthentication?
){
    if (auth != null) {
        when(val result = auth.signInAnonymously()){
            is AuthRes.Succes -> {
                viewModelAuthentication?.updateStateCurrentUser(newValue = StateCurrentUser.ACTIVE)
                analytics?.logButtonClicked("Click: Continuar como invitado")
                navigation.navigate(RoutesMainScreens.HomeScreen.route){
                    popUpTo(RoutesMainScreens.LoginScreen.route){
                        inclusive = true
                    }
                }
            }
            is AuthRes.Error -> {
                analytics?.logError("Error SignIn Incognito: ${result.errorMessage}")
            }
        }
    }
}
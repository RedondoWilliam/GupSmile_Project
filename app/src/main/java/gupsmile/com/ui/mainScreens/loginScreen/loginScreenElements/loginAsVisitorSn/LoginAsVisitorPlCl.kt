package gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginAsVisitorSn

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.model.AuthRes
//import gupsmile.com.firebaseManager.AuthRes
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.settingScreens.registerNewUserScreen.registerNewUserScreenElements.AlertDialogConfirmCreateAccount
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateCurrentUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateLoginAsVisitor
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateSignInUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication
import kotlinx.coroutines.launch

@Composable
fun LoginAsVisitorPanelControl(
    modifier: Modifier = Modifier,
    auth: AuthManager?,
    analytics: AnalitycsManager?,
    navController: NavHostController,
    viewModelAuthentication: ViewModelAuthentication
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val authenticationUiState = viewModelAuthentication.uiState.collectAsState().value

    var showDialog by remember{ mutableStateOf(false) }

    LoginAsVisitorSection(
        bottonActionsLoginAsVisitor = {
           scope.launch {
             viewModelAuthentication.loginAsVisitor()
           }
        }
    )

    when(authenticationUiState.stateLoginAsVisitor){
        StateLoginAsVisitor.SUCCESS ->{
            navController.navigate(RoutesMainScreens.HomeScreen.route){
                popUpTo(RoutesMainScreens.LoginScreen.route){
                    inclusive = true
                }
            }
            analytics?.logButtonClicked("Click: Continuar como invitado")
            viewModelAuthentication.updateStateCurrentUser(newValue = StateCurrentUser.ACTIVE)
           
        }
        StateLoginAsVisitor.ERROR -> {
            analytics?.logError("Error SignIn Incognito: ${authenticationUiState.errorLoginAsVisitor}")
            showDialog = true
        }
        StateLoginAsVisitor.UNSPECIFIED -> {}
        StateLoginAsVisitor.LOADING -> {}
    }

    if ( showDialog &&  authenticationUiState.stateLoginAsVisitor == StateLoginAsVisitor.ERROR ) {
        AlertDialogConfirmCreateAccount(
            onDismiss = {showDialog = false},
            onConfirmLogOut = {
                viewModelAuthentication.resetStateLoginAsVisitor()
                showDialog = false
            },
            titleAlertDialogMenu = "Ups! Algo salió mal. \n \n" +
            authenticationUiState.errorLoginAsVisitor,
            confirmBottomText = R.string.name_action_bottom_warning_error_crate_new_account,
            iconDialogMenu = R.drawable.warning_icon
        )
    }

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
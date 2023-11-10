package gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithGoogleScn

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import gupsmile.com.R
import gupsmile.com.data.MyModuleAuthentication
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.model.AuthRes
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.settingScreens.registerNewUserScreen.registerNewUserScreenElements.AlertDialogConfirmCreateAccount
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateLoginWithGoogle
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication
import kotlinx.coroutines.launch

@Composable
fun LoginWithGoogleScnPlCl(
    modifier: Modifier = Modifier,
    analytics: AnalitycsManager,
    navController: NavHostController,
    viewModelAuthentication: ViewModelAuthentication
){

    val context = LocalContext.current
    val authManager: AuthManager = MyModuleAuthentication.providesAutheticationManagerInstance(context)
    val scope = rememberCoroutineScope()
    val authenticationUiState = viewModelAuthentication.uiState.collectAsState().value

    var showDialog by remember {
        mutableStateOf(false)
    }


//    configuración de un Launcher para la actividad de inicio de sesión de google
    val googleSignInLauncher =
        rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ){result ->

        when(
            val account = authManager.handleSignInResult(
                GoogleSignIn.getSignedInAccountFromIntent(result.data)
            )
        ){
            is AuthRes.Succes ->{
                val credential = GoogleAuthProvider.getCredential(
                    account?.data?.idToken, null
                )
                scope.launch {
                    viewModelAuthentication.updateStateLoginWithGoogle(newValue = StateLoginWithGoogle.LOADING)
                    val fireUser = authManager.signInWithGoogleCredential(credential)
                    if(fireUser != null){
                        Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                        navController.navigate(RoutesMainScreens.HomeScreen.route){
                            popUpTo(RoutesMainScreens.LoginScreen.route){
                                inclusive = false
                            }
                        }
                        viewModelAuthentication.updateStateLoginWithGoogle(newValue = StateLoginWithGoogle.SUCCESS)
                    }
                }

            }
            is AuthRes.Error -> {
                showDialog = true
                analytics.logError("Error SignIn: ${account.errorMessage}")
                Toast.makeText(context, "Error  ${account.errorMessage}", Toast.LENGTH_SHORT).show()
                viewModelAuthentication.updateStateLoginWithGoogle(newValue = StateLoginWithGoogle.ERROR)
            }
            else -> {
                showDialog = true
                Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
                viewModelAuthentication.updateStateLoginWithGoogle(newValue = StateLoginWithGoogle.ERROR)
            }
        }

    }
    LoginWithGoogleScn(
        buttonAction = {
            authManager.signInwithGoogle(googleSignInLauncher)
        }
    )
    if(authenticationUiState.stateLoginWithGoogle == StateLoginWithGoogle.ERROR){
        AlertDialogConfirmCreateAccount(
            onDismiss = {showDialog = false},
            onConfirmLogOut = {
                viewModelAuthentication.updateStateLoginWithGoogle(newValue = StateLoginWithGoogle.UNSPECIFIED)
                showDialog = false
            },
            titleAlertDialogMenu = "Ups! Algo salió mal. \n \n" +
                    "No se ha podido iniciar sesión con Google.",
            confirmBottomText = R.string.name_action_bottom_warning_error_crate_new_account,
            iconDialogMenu = R.drawable.warning_icon
        )
    }
}
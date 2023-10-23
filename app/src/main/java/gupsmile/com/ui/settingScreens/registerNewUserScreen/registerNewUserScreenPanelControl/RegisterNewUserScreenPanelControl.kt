@file:Suppress("UnusedEquals")

package gupsmile.com.ui.settingScreens.registerNewUserScreen.registerNewUserScreenPanelControl

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.analytics.FirebaseAnalytics
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
//import gupsmile.com.firebaseManager.AuthRes
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.settingScreens.registerNewUserScreen.registerNewUserScreenElements.AlertDialogConfirmCreateAccount
import gupsmile.com.ui.settingScreens.registerNewUserScreen.registerNewUserScreenElements.RegisterNewUserScreen
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.RegisterNewUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateSignInUser
import gupsmile.com.ui.viewModelPanelControl.viewModelTranslate.ViewModelTranslate

@Composable
fun RegisterNewUserScreenPanelControl(
    modifier: Modifier = Modifier,
    analytics: AnalitycsManager,
    navController: NavHostController,
    auth: AuthManager,
    viewModelAuthentication: ViewModelAuthentication
){

//    val viewModelAuthentication: ViewModelAuthentication = viewModel()
    val authenticationUiState = viewModelAuthentication.uiState.collectAsState().value

    val viewModelTranslate: ViewModelTranslate  = viewModel()

    analytics?.logScreenView(screenName = RoutesMainScreens.CreateNewAccount.route)

    var showDialog by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val arrowBackAction = {
        navController.navigate(RoutesMainScreens.LoginScreen.route) {
            popUpTo(RoutesMainScreens.LoginScreen.route) {
                inclusive = true
            }
        }
    }


    BackHandler {
       arrowBackAction()
    }

    RegisterNewUserScreen(
        email = authenticationUiState.emailRegisterNewUser,
        password = authenticationUiState.passwordRegisterNewUser,
        confirmPassword = authenticationUiState.confirmPasswordRegisterNewUser,
        ontextChangeEmail = {
            viewModelAuthentication.updateEmailRegisterNewUser(newValue = it)
        },
        onTextChangePassword = {
            viewModelAuthentication.updatePasswordRegisterNewUser(newValue = it)
        },
        onTextConfirmPassword = {
            viewModelAuthentication.updateConfirmPasswordRegisterNewUser(newValue = it)
        },
        bottonActions = {
            viewModelAuthentication.registerNewUser()
        },
        arrowBackTopBottom = { arrowBackAction() },
        existAccountActions = { arrowBackAction() },
        auth = auth,

    )


    when(authenticationUiState.stateRegisterNewUser ){
        RegisterNewUser.SUCCESS ->{
            showDialog = true
            analytics.logButtonClicked(FirebaseAnalytics.Event.SIGN_UP)
        }
        RegisterNewUser.ERROR -> {
            showDialog = true
            analytics.logButtonClicked(
                "Error SignUP: ${authenticationUiState.typeErrorRegisterNewUser}"
            )


        }
        RegisterNewUser.SPACEEMPTY ->{
           showDialog = true
        }
        RegisterNewUser.UNSPECIFY -> {}

    }


    if ( showDialog &&  authenticationUiState.stateRegisterNewUser == RegisterNewUser.SUCCESS ) {
        AlertDialogConfirmCreateAccount(
            onDismiss = {showDialog = false },
            onConfirmLogOut = {
                viewModelAuthentication.resetElementsRegisterNewUser()
                showDialog = false
                              },
            titleAlertDialogMenu = "Cuenta creada exitosamente",
            confirmBottomText = R.string.name_bottom_alert_dialog_retrieve_password_alert_dialog,
            iconDialogMenu = R.drawable.succes_create_account_icon
        )
    }

    if ( showDialog &&  authenticationUiState.stateRegisterNewUser == RegisterNewUser.ERROR ) {
        AlertDialogConfirmCreateAccount(
            onDismiss = {showDialog = false},
            onConfirmLogOut = {
                viewModelAuthentication.resetElementsRegisterNewUser()
                showDialog = false
            },
            titleAlertDialogMenu = "Ups! Algo salió mal. \n \n" +
                    viewModelTranslate.getTextTranslated(authenticationUiState.typeErrorRegisterNewUser),
            confirmBottomText = R.string.name_action_bottom_warning_error_crate_new_account,
            iconDialogMenu = R.drawable.warning_icon
        )
    }

    if ( showDialog &&  authenticationUiState.stateRegisterNewUser == RegisterNewUser.SPACEEMPTY ) {
        AlertDialogConfirmCreateAccount(
            onDismiss = {showDialog = false},
            onConfirmLogOut = {
                viewModelAuthentication.resetElementsRegisterNewUser()
                showDialog = false
            },
            titleAlertDialogMenu = "Ups! Algo salió mal. \n \n" +
                    "Los espacios para correo y contraseña aún están vacíos",
            confirmBottomText = R.string.name_action_bottom_warning_error_crate_new_account,
            iconDialogMenu = R.drawable.warning_icon
        )
    }
}

package gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithPhoneOrEmailSn.loginWithPhoneOrEmailSnPlCl

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.analytics.FirebaseAnalytics
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.model.AuthRes
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithPhoneOrEmailSn.loginWithPhoneOrEmailSnEs.LoginWithPhoneOrEmailScn
//import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithPhoneOrEmailSn.loginWithPhoneOrEmailSnEs.LoginWithPhoneOrEmailSection
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.settingScreens.registerNewUserScreen.registerNewUserScreenElements.AlertDialogConfirmCreateAccount
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateCurrentUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateSignInUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context


@Composable
fun LoginWithPhoneOrEmailScnPlCl(
    analytics: AnalitycsManager,
    navController: NavHostController,
    auth: AuthManager,
    viewModelAuthentication: ViewModelAuthentication
){
    val authenticationUiState = viewModelAuthentication.uiState.collectAsState().value

    var showDialog by remember {
        mutableStateOf(false)
    }

    LoginWithPhoneOrEmailScn(
        email = authenticationUiState.signInUserEmail,
        password = authenticationUiState.signInUserPassword,
        onTextChangeEmail = {
            viewModelAuthentication.updateSignInUserEmail(newValue = it)
        },
        onTextChangePassword = {
            viewModelAuthentication.updateSignInUserPassword(newValue = it)
        },
        bottomActions = {
            viewModelAuthentication.signInUser()
        }
    )

    when(authenticationUiState.stateSignInUser ){
        StateSignInUser.SUCCESS ->{
            showDialog = true
            viewModelAuthentication.updateStateCurrentUser(newValue = StateCurrentUser.ACTIVE)
            analytics.logButtonClicked(FirebaseAnalytics.Event.LOGIN)
            viewModelAuthentication.resetElementsSignInUser()
            navController.navigate(RoutesMainScreens.HomeScreen.route){
                popUpTo(RoutesMainScreens.LoginScreen.route) { inclusive = true }
            }
        }
        StateSignInUser.ERROR -> {
            showDialog = true
            analytics.logButtonClicked(
                "Error SignUP: ${authenticationUiState.typeErrorSignInUser}"
            )
        }
        StateSignInUser.SPACEEMPTY ->{
            showDialog = true
        }
        StateSignInUser.UNSPECIFY -> {}
        StateSignInUser.LOADING -> {}
    }

    if ( showDialog && authenticationUiState.stateSignInUser == StateSignInUser.SUCCESS ) {
    }

    if ( showDialog &&  authenticationUiState.stateSignInUser == StateSignInUser.ERROR ) {
        AlertDialogConfirmCreateAccount(
            onDismiss = {showDialog = false},
            onConfirmLogOut = {
               viewModelAuthentication.resetElementsSignInUser()
                showDialog = false
            },
            titleAlertDialogMenu = "Ups! Algo salió mal. \n \n" +
                    authenticationUiState.typeErrorSignInUser,
            confirmBottomText = R.string.name_action_bottom_warning_error_crate_new_account,
            iconDialogMenu = R.drawable.warning_icon
        )
    }

    if ( showDialog &&  authenticationUiState.stateSignInUser == StateSignInUser.SPACEEMPTY ) {
        AlertDialogConfirmCreateAccount(
            onDismiss = {showDialog = false},
            onConfirmLogOut = {
                viewModelAuthentication.resetElementsSignInUser()
                showDialog = false
            },
            titleAlertDialogMenu = "Ups! Algo salió mal. \n \n" +
                   "Los espacios para correo y contraseña aún están vacíos",
            confirmBottomText = R.string.name_action_bottom_warning_error_crate_new_account,
            iconDialogMenu = R.drawable.warning_icon
        )
    }
}

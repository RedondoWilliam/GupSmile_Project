package gupsmile.com.ui.settingScreens.retrievePasswordScreen.retrievePasswordScreenPanelControl

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.model.AuthRes
import gupsmile.com.ui.commonElements.DialogLoading
import gupsmile.com.ui.commonElements.LogOutDialogMenu
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.settingScreens.registerNewUserScreen.registerNewUserScreenElements.AlertDialogConfirmCreateAccount
import gupsmile.com.ui.settingScreens.retrievePasswordScreen.retrievePasswordScreenElements.PruebaDialogo
import gupsmile.com.ui.settingScreens.retrievePasswordScreen.retrievePasswordScreenElements.RetrievePasswordScreen
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateRetrievePassoword
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication
import kotlinx.coroutines.launch

@Composable
fun RetrievePasswordScreenPanelControl(
    modifier: Modifier = Modifier,
    analytics: AnalitycsManager,
    navController: NavHostController,
    auth: AuthManager?,
    viewModelAuthentication: ViewModelAuthentication
){

//    val viewModelAuthentication: ViewModelAuthentication = viewModel()
    val authenticationUiState = viewModelAuthentication.uiState.collectAsState().value


    analytics.logScreenView(screenName = RoutesMainScreens.RetrieveScreen.route)
    var showDialog by remember{ mutableStateOf(false) }

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


   Box(
       modifier = modifier
           .fillMaxSize()
           .navigationBarsPadding()
           .statusBarsPadding(),
       contentAlignment = Alignment.Center
   ) {
       RetrievePasswordScreen(
           bottomAction = {
               viewModelAuthentication.retrievePassword()
           },
           arrowBackBottom = {
               arrowBackAction()
           },
           onTextChange = {
               viewModelAuthentication.updateEmailRetrievePassword(newValue = it)
           },
           email = authenticationUiState.emailRetrievePassword
       )

       if(authenticationUiState.stateRetrievePassoword == StateRetrievePassoword.LOADING){
           DialogLoading()
       }

   }

    when(authenticationUiState.stateRetrievePassoword ){
        StateRetrievePassoword.SUCCESS -> {showDialog = true}
        StateRetrievePassoword.ERROR -> {showDialog = true}
        StateRetrievePassoword.UNSPECIFY -> {}
        StateRetrievePassoword.SPACEEMPTY -> {showDialog = true}
        StateRetrievePassoword.LOADING -> {}
    }

    if (
        showDialog && authenticationUiState.stateRetrievePassoword == StateRetrievePassoword.SUCCESS
        ) {
        PruebaDialogo(
            onDismiss = {showDialog = false},
            onConfirmLogOut = {
                showDialog = false
                viewModelAuthentication.resetElementsRetrievePassword()
            },
            titleAlertDialogMenu = R.string.title_alert_dialog_retrieve_password_alert_dialog,
            confirmBottomText = R.string.name_bottom_alert_dialog_retrieve_password_alert_dialog,
            titheAlertTwo ="${authenticationUiState.emailRetrievePassword}",
            titleAlertThree = R.string.title_alert_dialog_retrieve_password_alert_dialog_three
        )
    }
    if (showDialog && authenticationUiState.stateRetrievePassoword == StateRetrievePassoword.ERROR){
        AlertDialogConfirmCreateAccount(
            onDismiss = {showDialog = false},
            onConfirmLogOut = {
                viewModelAuthentication.resetElementsRetrievePassword()
                showDialog = false
            },
            titleAlertDialogMenu = "Ups! Algo salió mal.\n \n" +
                    authenticationUiState.errorRetrievePassword,
            confirmBottomText = R.string.name_action_bottom_warning_error_crate_new_account,
            iconDialogMenu = R.drawable.warning_icon
        )
    }
    if (showDialog
        && authenticationUiState.stateRetrievePassoword == StateRetrievePassoword.SPACEEMPTY
        ){
        AlertDialogConfirmCreateAccount(
            onDismiss = {showDialog = false},
            onConfirmLogOut = {
                viewModelAuthentication.resetElementsRetrievePassword()
                showDialog = false
            },
            titleAlertDialogMenu = "Ups! Algo salió mal.\n \n" +
                    "El espacio para correo electrónico aún está vacío.",
            confirmBottomText = R.string.name_action_bottom_warning_error_crate_new_account,
            iconDialogMenu = R.drawable.warning_icon
        )
    }

}
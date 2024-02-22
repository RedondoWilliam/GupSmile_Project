package gupsmile.com.ui.subScreens.infoLocalContactSn.infoLocalContactPlCl

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
//import androidx.compose.ui.tooling.data.EmptyGroup.name
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ensayo.example.ui.commonElements.textCommonHomePage
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.data.MyModuleAuthentication
import gupsmile.com.model.Contact
import gupsmile.com.ui.commonElements.DialogOneOptionPdCnEs
import gupsmile.com.ui.commonElements.DialogOptionsPersonalized
import gupsmile.com.ui.commonElements.SimpleDialogPd
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.subScreens.infoLocalContactSn.infoLocalContactEs.InfoLocalContactSn
//import gupsmile.com.ui.viewModelPanelControl.viewModelContacts.ViewModelContacts
import java.util.concurrent.Flow

@Composable
fun InfoLocalContactPlCl(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    contactKey: String?
){


    val context = LocalContext.current
    val authManager = MyModuleAuthentication.providesAutheticationManagerInstance(context)
    val realTimeManager = MyModuleAuthentication.providesRealTimeManagerInstance(context)
//    val viewModelContacts : ViewModelContacts = viewModel()
//    val contactsUiState = viewModelContacts.uiState.collectAsState().value


    var showDialog by  remember{ mutableStateOf(false) }
    var deleteInfo by  remember{ mutableStateOf(false) }
    var showDialogConfirmedDeleteContact by remember { (mutableStateOf(false))}
    val onDeleteContactConfirmed: () -> Unit = {
       realTimeManager.deleteContact(contactKey.toString())
        deleteInfo = true
    }
    val contact = realTimeManager.showContact(contactKey.toString()).collectAsState(Contact())

    BackHandler {
        showDialogConfirmedDeleteContact = false
        navController.navigate(route = RoutesMainScreens.ContactsScreen.route){
            popUpTo(route = RoutesMainScreens.InfoLocalContactPlCl.route){
                inclusive = false
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {
        if(!deleteInfo){
            InfoLocalContactSn(
                arrowBackTopBottom = {},
                textNameUser  = contact.value.name + contact.value.lastName,
                textPhoneUser = contact.value.phoneNumber,
                textEmailUser =contact.value.email,
                textAddressUser = contact.value.address,
                editContactActionBottom = {
                    navController.navigate(RoutesMainScreens.AddContactLocalSnPlCl.route + "/${contactKey.toString()}")
                },
                deleteContactActionBottom = {
                    showDialog = true
                },
                emailBottomBarActionBottom = {},
                chatBottomBarActionBottom = {},
                phoneBottomBarActionBottom = {}
            )
        }else{
            Column (
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                SimpleDialogPd(titleDialog = R.string.title_dialog_there_is_no_information_by_show)
            }
        }
        if(showDialog){
            DialogOptionsPersonalized(
                onDismiss = { showDialog = false },
                onConfirmActions = {
                    onDeleteContactConfirmed()
                    showDialog = false
                    showDialogConfirmedDeleteContact = true
                                   },
                textCancelBottom = R.string.text_action_bottom_cancel,
                textConfirmBottom = R.string.text_action_bottom_aceptar,
                textInfoDialogMenu = R.string.text_content_dialog_delete_contact,
                iconDialogMenu = R.drawable.warning_icon
            )
        }
        if(showDialogConfirmedDeleteContact){
            DialogOneOptionPdCnEs(
                onDismiss = { /*TODO*/ },
                onConfirmActions = {
                    showDialogConfirmedDeleteContact = false
                   navController.navigate(route = RoutesMainScreens.ContactsScreen.route){
                       popUpTo(route = RoutesMainScreens.AddContactLocalSnPlCl.route){
                           inclusive = false
                       }
                   }
                },
                textConfirmBottom = R.string.title_bottom_dialog_confirm_delete_contact,
                textInfoDialogMenu = R.string.title_dialog_confirm_delete_contact,
                iconDialogMenu = R.drawable.succes_create_account_icon
            )
        }
    }
}
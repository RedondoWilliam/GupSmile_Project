package gupsmile.com.ui.mainScreens.contactsSn.ContactsSnPlCl

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import gupsmile.com.R
import gupsmile.com.data.MyModuleAuthentication
import gupsmile.com.model.Contact
import gupsmile.com.ui.mainScreens.contactsSn.contactsSnEs.ContactListItem
import gupsmile.com.ui.mainScreens.contactsSn.contactsSnEs.ContactsSn
import gupsmile.com.ui.mainScreens.messagesScreen.messagesSnEs.DialogWithoutChats
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication
import gupsmile.com.ui.viewModelPanelControl.viewModelContacts.ViewModelContacts

@Composable
fun ContactsSnPlCl(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigateToAddContact: () -> Unit
){

    BackHandler {
        navController.navigate(route = RoutesMainScreens.MessagesScreen.route){
            popUpTo(route = RoutesMainScreens.ContactsScreen.route){
                inclusive = false
            }
        }
    }

    val context = LocalContext.current
    val realTimeManager = MyModuleAuthentication.providesRealTimeManagerInstance(context)

    val viewModelContacts : ViewModelContacts = viewModel()
    val contactsUiState = viewModelContacts.uiState.collectAsState().value


    /**
     * obtenemos el flujo de contactos listo para poder utilizarlo
     * */
    val contacts by realTimeManager.getContactsFlow().collectAsState(emptyList())

    ContactsSn(
        arrowBackTopBottom = {
           navController.navigate(route = RoutesMainScreens.MessagesScreen.route){
               popUpTo(route = RoutesMainScreens.ContactsScreen.route){
                   inclusive = false
               }
           }
        },
        addContactActionBottom = {
           navigateToAddContact()
        },
        addNewContact = @Composable{
            /**
             * en caso de que la lista de contactos no sea nula o vacía*/
           if(!contacts.isNullOrEmpty()){
               contacts.forEach{contact ->
                       ContactListItem(
                           bottomActionsProfileImage = { /*TODO*/ },
                           profileImage = painterResource(id = R.drawable.selfie),
                           contact = contact,
                           navController = navController
                       )
               }
           }else{
               Column(
                   modifier = Modifier.fillMaxWidth(),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Spacer(modifier = Modifier.height(130.dp))
                   DialogWithoutChats(
                       contentTextDialog = R.string.dialog_without_contacts_contacts_screen
                   )
               }
           }
        }
    )
}
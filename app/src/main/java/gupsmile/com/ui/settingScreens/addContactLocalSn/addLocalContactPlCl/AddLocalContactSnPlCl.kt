package gupsmile.com.ui.settingScreens.addContactLocalSn.addLocalContactPlCl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import gupsmile.com.R
import gupsmile.com.data.MyModuleAuthentication
import gupsmile.com.data.firebaseManager.RealTimeManager
import gupsmile.com.model.Contact
import gupsmile.com.ui.commonElements.DialogOneOptionPdCnEs
import gupsmile.com.ui.commonElements.DialogOptionsPersonalized
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.settingScreens.addContactLocalSn.addLocalContactEs.AddContactLocalSn

@Composable
fun AddContactLocalSnPlCl(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    contactKey: String?
){
    val context = LocalContext.current
    val authManager = MyModuleAuthentication.providesAutheticationManagerInstance(context)
    val realTimeManager = MyModuleAuthentication.providesRealTimeManagerInstance(context)
    val editContact = realTimeManager.showContact(contactKey.toString()).collectAsState(Contact())
    var name by remember{ mutableStateOf(if(contactKey== null || contactKey == "{contactKey}") "" else "  ") }
    var phoneNumber by remember{ mutableStateOf(if(contactKey== null  || contactKey == "{contactKey}") "" else "  ") }
    var lastName by remember{ mutableStateOf(if(contactKey== null  || contactKey == "{contactKey}")  "" else "  ") }
    var address by remember{ mutableStateOf(if(contactKey== null  || contactKey == "{contactKey}" ) "" else "  ") }
    var email by remember{ mutableStateOf(if(contactKey== null  || contactKey == "{contactKey}" ) "" else "  ") }
    var uid = authManager.getCurrentUser()?.uid

    var showDialog by  remember{ mutableStateOf(false) }

    var showDialogEditContact by  remember{ mutableStateOf(false) }





    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        if( contactKey == "{contactKey}" || contactKey == null ){
            AddContactLocalSn(
                arrowBackTopBottom = {},
                bottomActionSaveContact = {
                    showDialog = true
                    val newContact = Contact(
                        name = name,
                        phoneNumber = phoneNumber,
                        email = email,
                        lastName = lastName,
                        address  = address,
                        uid = uid.toString()
                    )
                    realTimeManager.addContact(newContact)
                    name = ""
                    phoneNumber = ""
                    email = ""
                    lastName = ""
                    address = ""
                },
                textEntryName = name,
                textEntryLastName = lastName,
                textEntryPhone = phoneNumber,
                textEntryAddress = address,
                textEntryEmail = email,
                onTextEntryNameChange = {name = it},
                onTextEntryLastNameChange = {lastName = it},
                onTextEntryPhoneChange = {phoneNumber = it},
                onTextEntryAddressChange = {address = it},
                onTextEntryEmailChange = {email = it},
                menuOptionDismissActions = {}
            )

            if(showDialog){
                DialogOneOptionPdCnEs(
                    onDismiss = { showDialog = false },
                    onConfirmActions = { showDialog = false },
                    textConfirmBottom = R.string.text_action_bottom_aceptar,
                    textInfoDialogMenu = R.string.dialog_contac_added ,
                    iconDialogMenu = R.drawable.succes_create_account_icon
                )
            }
        }else{
            AddContactLocalSn(
                arrowBackTopBottom = {},
                bottomActionSaveContact = {
                    val newContact = Contact(
                        name = if(name == "  " || name == editContact.value.name) editContact.value.name else name,
                        phoneNumber =if(phoneNumber == "  " || phoneNumber == editContact.value.phoneNumber) editContact.value.phoneNumber else phoneNumber,
                        email = if(email== "  " || email == editContact.value.email) editContact.value.email else email,
                        lastName = if(lastName == "  " || lastName == editContact.value.lastName) editContact.value.lastName else lastName,
                        address  = if(address == "  " || address == editContact.value.address) editContact.value.address else address,
                        uid = uid.toString()
                    )
                    realTimeManager.updateContact(
                        contacId = contactKey,
                        updatedContact = newContact
                    )
                    name = ""
                    phoneNumber = ""
                    email = ""
                    lastName = ""
                    address = ""
                    showDialogEditContact = true
                },

                textEntryName =  if(name == "  " ) editContact.value.name  else name,
                textEntryLastName = if(lastName == "  ") editContact.value.lastName else lastName,
                textEntryPhone = if(phoneNumber == "  ") editContact.value.phoneNumber else phoneNumber,
                textEntryAddress = if(address == "  ") editContact.value.address else address,
                textEntryEmail = if(email == "  ") editContact.value.email else email,
                onTextEntryNameChange = { name = it},
                onTextEntryLastNameChange = {lastName = it},
                onTextEntryPhoneChange = {phoneNumber= it},
                onTextEntryAddressChange = {address= it},
                onTextEntryEmailChange = {email = it},
                menuOptionDismissActions = {}
            )

            if(showDialogEditContact){
                DialogOneOptionPdCnEs(
                    onDismiss = {
                        showDialogEditContact = false
                                },
                    onConfirmActions = {
                        showDialogEditContact = false
                        navController.navigate(route = RoutesMainScreens.InfoLocalContactPlCl.route + "/${contactKey}"){
                            popUpTo(route = RoutesMainScreens.AddContactLocalSnPlCl.route){
                                inclusive = false
                            }
                        }
                                       },
                    textConfirmBottom = R.string.title_bottom_dialog_confirm_delete_contact,
                    textInfoDialogMenu = R.string.dialog_contact_edited ,
                    iconDialogMenu = R.drawable.succes_create_account_icon
                )
            }
        }
    }
}
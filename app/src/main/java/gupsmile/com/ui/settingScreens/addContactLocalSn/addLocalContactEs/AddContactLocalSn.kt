package gupsmile.com.ui.settingScreens.addContactLocalSn.addLocalContactEs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import gupsmile.com.R
import gupsmile.com.ui.commonElements.DropDownMenuItemPersonalized
import gupsmile.com.ui.commonElements.PersonalizedBottomActions
import gupsmile.com.ui.commonElements.TextfieldPersonalized
import gupsmile.com.ui.commonElements.TopAppBarPersonalized
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun AddContactLocalSn(
    modifier: Modifier = Modifier,
    arrowBackTopBottom: () -> Unit,
    bottomActionSaveContact: ()-> Unit,
    textEntryName: String,
    textEntryLastName: String,
    textEntryPhone: String,
    textEntryAddress: String,
    textEntryEmail: String,
    onTextEntryNameChange: (String)-> Unit,
    onTextEntryLastNameChange: (String)-> Unit,
    onTextEntryPhoneChange: (String)-> Unit,
    onTextEntryAddressChange: (String)-> Unit,
    onTextEntryEmailChange:(String)-> Unit,
    menuOptionDismissActions:() -> Unit



){
    val columnStateAddContactScreen = rememberScrollState()
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember{ mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(columnStateAddContactScreen)
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopAppBarPersonalized(
                    arrowBackTopBottom = {arrowBackTopBottom()},
                    titleTopAppBar = R.string.add_contact_screen_title,
                    bottomMenuActions = { expanded = !expanded }
                )
                ImageContactScn()
                PersonalizedBottomActions(
                    actionsBottomText = R.string.bottom_title_new_contact,
                    onConfirmActions = {bottomActionSaveContact()}
                )
                Spacer(modifier = modifier.height(30.dp))
                TextfieldPersonalized(
                    textEntry = textEntryName,
                    onTextChange= {it:String ->
                        onTextEntryNameChange(it)
                    },
                    focusRequester = FocusRequester(),
                    iconItem =  painterResource(id = R.drawable.contact_new_contact),
                    textEntryDefault = R.string.name_new_contact
                )
                Spacer(modifier = modifier.height(25.dp))
                TextfieldPersonalized(
                    textEntry =textEntryLastName,
                    onTextChange= {it:String ->
                        onTextEntryLastNameChange(it)
                    },
                    focusRequester = FocusRequester(),
                    iconItem = painterResource(id = R.drawable.contact_new_contact),
                    textEntryDefault =  R.string.last_name_new_contact
                )
                Spacer(modifier = modifier.height(25.dp))
                TextfieldPersonalized(
                    textEntry = textEntryPhone,
                    onTextChange= {it:String ->
                        onTextEntryPhoneChange(it)
                    },
                    focusRequester = FocusRequester(),
                    iconItem = painterResource(id = R.drawable.phone_icon_add_contact),
                    textEntryDefault =  R.string.phone_new_contact
                )
                Spacer(modifier = modifier.height(25.dp))
                TextfieldPersonalized(
                    textEntry =textEntryAddress,
                    onTextChange={it:String ->
                        onTextEntryAddressChange(it)
                    },
                    focusRequester = FocusRequester(),
                    iconItem = painterResource(id = R.drawable.addres_icon_add_contact),
                    textEntryDefault =   R.string.adress_new_contact
                )
                Spacer(modifier = modifier.height(25.dp))
                TextfieldPersonalized(
                    textEntry = textEntryEmail,
                    onTextChange={it:String ->
                        onTextEntryEmailChange(it)
                    },
                    focusRequester = FocusRequester(),
                    iconItem = painterResource(id = R.drawable.emailicon),
                    textEntryDefault =    R.string.email_new_contact
                )

            }

        }

        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = modifier
                .padding(start = (15).dp, top =(15).dp)
                .offset(x = (210).dp, y = (50).dp)
        ) {
            DropdownMenu(
                offset = DpOffset.Zero,
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = modifier.background(MaterialTheme.colorScheme.secondaryContainer)
            )
            {
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.edit_icon,
                            nameItemMenu = R.string.menu_options_dismiss
                        )
                    },
                    onClick = {menuOptionDismissActions()}
                )

            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddContactLocalSnPreview(){
    GupsMileTheme {
        AddContactLocalSn(
            arrowBackTopBottom = {},
            bottomActionSaveContact = {},
            textEntryName = "",
            textEntryLastName = "",
            textEntryPhone = "",
            textEntryAddress = "",
            textEntryEmail = "",
            onTextEntryNameChange = {},
            onTextEntryLastNameChange = {},
            onTextEntryPhoneChange = {},
            onTextEntryAddressChange = {},
            onTextEntryEmailChange = {},
            menuOptionDismissActions = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AddContactLocalDarkModeSnPreview(){
    GupsMileTheme(
        darkTheme = true
    ) {
        AddContactLocalSn(
            arrowBackTopBottom = {},
            bottomActionSaveContact = {},
            textEntryName = "",
            textEntryLastName = "",
            textEntryPhone = "",
            textEntryAddress = "",
            textEntryEmail = "",
            onTextEntryNameChange = {},
            onTextEntryLastNameChange = {},
            onTextEntryPhoneChange = {},
            onTextEntryAddressChange = {},
            onTextEntryEmailChange = {},
            menuOptionDismissActions = {}
        )
    }
}
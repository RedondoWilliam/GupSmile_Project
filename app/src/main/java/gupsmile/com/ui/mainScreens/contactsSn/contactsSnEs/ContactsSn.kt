package gupsmile.com.ui.mainScreens.contactsSn.contactsSnEs

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.commonElements.TopAppBarPersonalized
import gupsmile.com.ui.mainScreens.messagesScreen.messagesSnEs.DialogWithoutChats
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun ContactsSn(
    modifier : Modifier = Modifier,
    arrowBackTopBottom: () -> Unit,
    addContactActionBottom: ()-> Unit,
    addNewContact: @Composable () -> Unit
){
    val columnStateContactsScreen = rememberScrollState()

    Box(
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(columnStateContactsScreen),

            ) {
            TopAppBarPersonalized(
                arrowBackTopBottom = {},
                titleTopAppBar = R.string.contacts_title
            )
            Spacer(modifier = modifier.height(15.dp))
            SubTopBarToolsScn(
                addContactActionBottom =addContactActionBottom
            )

            addNewContact()

        }


    }

}


@Preview(showBackground = true)
@Composable
fun ContactsSnPreview(){
    GupsMileTheme {
        ContactsSn(
            arrowBackTopBottom = {},
            addContactActionBottom = {},
            addNewContact = @Composable{
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
        )
    }
}
package gupsmile.com.ui.mainScreens.messagesScreen.messagesSnEs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun HomeMessagesSn(
    modifier : Modifier = Modifier,
    arrowBackTopBottom: () -> Unit,
    bottomContactActions: () -> Unit,
    contactsList: @Composable () -> Unit
){
    Box(
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),

        ) {
            TopAppBarPersonalized(
                arrowBackTopBottom = {},
                titleTopAppBar = R.string.chat_title,
                bottomMenuActions = {}
            )
            Spacer(modifier = modifier.height(15.dp))
            SubTopBarToolsScn(
                bottomContactActions = bottomContactActions
            )
            contactsList()

        }

    }

}


@Preview(showBackground = true)
@Composable
fun HomeMessagesSnPreview(){
    GupsMileTheme {
        HomeMessagesSn(
            arrowBackTopBottom = {},
            bottomContactActions = {},
            contactsList = @Composable{
                DialogWithoutChats(
                    contentTextDialog = R.string.dialog_without_contacts_messages_screen
                )
            }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun HomeMessagesSnDarkModePreview(){
    GupsMileTheme(
        darkTheme = true
    ) {
        HomeMessagesSn(
            arrowBackTopBottom = {},
            bottomContactActions = {},
            contactsList = @Composable{
                DialogWithoutChats(
                    contentTextDialog = R.string.dialog_without_contacts_messages_screen
                )
            }
        )
    }
}
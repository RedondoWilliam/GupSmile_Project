package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeSubTopBarOptions.homeSubTopBarOptionsPanelControl

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeSubTopBarOptions.homeSubTopBarOptionsElements.HomeSubTopBarOptionsElements

@Composable
fun HomeSubTopBarOptionsPanelControl(
    modifier: Modifier = Modifier,
    auth: AuthManager?,
    messagesBottomActions: () -> Unit
){
    val user = auth?.getCurrentUser()

    HomeSubTopBarOptionsElements(
        nameUser = if(!user?.email.isNullOrEmpty())"${user?.email}" else "Anónimo",
        messagesBottomActions = {messagesBottomActions()}
    )
}
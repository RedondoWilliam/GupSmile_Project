package gupsmile.com.ui.mainScreens.SearchScreen.SearchScreenElements.homeSearchBar.homeSearchBarPanelControl

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import gupsmile.com.ui.mainScreens.SearchScreen.SearchScreenElements.homeSearchBar.homeSearchBarElements.HomeSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearchPanelControl(
    modifier: Modifier = Modifier
){
    HomeSearchBar(
        searchWidgetState = SearchWidgetState.OPENED,
        scroll = null,
        text = "" ,
        ontextchage = {},
        onCloseClicked = { /*TODO*/ },
        onSearchClickedTwo = {},
        focusRequester = FocusRequester()
    )
}
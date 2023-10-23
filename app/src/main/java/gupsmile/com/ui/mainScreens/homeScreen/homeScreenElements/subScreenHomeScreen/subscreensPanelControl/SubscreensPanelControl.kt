package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.subScreensHomeScreen.subscreensPanelControl

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.subScreenHomeScreen.homeSubScreensElements.homeScreenMyReviews.HomeScreenMyReviews
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.subScreenHomeScreen.homeSubScreensElements.homeScreenPost.HomeScreenPostPanelConrtrol
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.subScreenHomeScreen.homeSubScreensElements.homeScreenSaved.HomeScreenSaved
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.subScreenHomeScreen.homeSubScreensElements.homeScreenYourPeople.homeScreenYourPeopleElements.HomeScreenYourPeopleElements
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.subScreenHomeScreen.subscreensPanelControl.subscreensManagerState.ViewModelHorizontalPagerPage
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SubscreensPanelControl(
    modifier: Modifier = Modifier,
    scrollBehavior : TopAppBarScrollBehavior,
    columnState : ScrollState = rememberScrollState(),

    ){
    val viewModelHorizontalPager: ViewModelHorizontalPagerPage = viewModel()
    val horizontalPagerUiState = viewModelHorizontalPager.uiState.collectAsState().value

    val pagerState = rememberPagerState(
        pageCount = {4}
    )
    viewModelHorizontalPager.updateNamePage(newValue = pagerState.currentPage)

    HorizontalPager(
        state = pagerState
    ) {
        page ->
        when(page){
            0 -> { HomeScreenPostPanelConrtrol(
                columnState = columnState,
                scrollBehavior = scrollBehavior
                    )
             }
            1 -> { HomeScreenMyReviews()}
            2 -> { HomeScreenSaved()}
            3 -> {HomeScreenYourPeopleElements()}
        }
    }
}
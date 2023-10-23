package gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileNavigationUpSections.profileNavigationUpSectionsPanelControl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
//import gupsmile.com.ui.screens.homeScreen.HomeScreenElements.homeNavigationUpSections.homeNavigationUpSectionElements.HomeNavigationUpSections
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileNavigationUpSections.profileNavigationUpSectionsElements.ProfileNavigationUpSections
//import gupsmile.com.ui.screens.homeScreen.HomeScreenElements.subScreensHomeScreen.subscreensPanelControl.subscreensManagerState.ViewModelHorizontalPagerPage
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.ProfileSbsSnPlCl.profileSubscreensManagerState.ViewModelProfileHorizontalPagerPage

@Composable
fun HomeProfileNavigationUpSectionsPanelControl(
    modifier: Modifier = Modifier
){

    val viewModelProfileHorizontalPager: ViewModelProfileHorizontalPagerPage = viewModel()
    val profileHorizontalPagerUiState =
        viewModelProfileHorizontalPager.uiState.collectAsState().value
    ProfileNavigationUpSections(
        viewModelProfileHorizontalPagerPage = viewModelProfileHorizontalPager,
        profileHorizontalPagerPageUiState = profileHorizontalPagerUiState
    )
}
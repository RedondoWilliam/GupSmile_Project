package gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.ProfileSbsSnPlCl

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.ProfileSbsSnPlCl.profileSubscreensManagerState.ViewModelProfileHorizontalPagerPage
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.profileSbSnsEs.sbSnInformationContact.sbSnInformationContactPlCl.SubScreenInformationContactPanelControl
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.profileSbSnsEs.subScreenPhotos.SubScreenPhotos
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.profileSbSnsEs.subScreenReviews.SubScreenReviews
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.profileSbSnsEs.subScreenVideos.SubScreenVideos

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileSubscreensPanelControl(
    modifier: Modifier = Modifier,

    ){
    val viewModelProfileHorizontalPager: ViewModelProfileHorizontalPagerPage = viewModel()
    val horizontalPagerUiState = viewModelProfileHorizontalPager.uiState.collectAsState().value

    val pagerState = rememberPagerState(
        pageCount = {4}
    )
    viewModelProfileHorizontalPager.updateNamePage(newValue = pagerState.currentPage)

    HorizontalPager(
        state = pagerState
    ) {
            page ->
        when(page){
            0 -> {
                    SubScreenInformationContactPanelControl()
                }
            1 -> {
                    SubScreenReviews()
                }
            2 -> {
                   SubScreenPhotos()
                }
            3 -> {
                    SubScreenVideos()
                }
        }
    }
}
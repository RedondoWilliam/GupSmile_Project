package gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileNavigationUpSections.profileNavigationUpSectionsElements

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
//import gupsmile.com.ui.screens.homeScreen.HomeScreenElements.homeNavigationUpSections.homeNavigationUpSectionPanelControl.NavigationUpOptions
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileNavigationUpSections.profileNavigationUpSectionsPanelControl.ProfileNavigationUpOptions
//import gupsmile.com.ui.screens.homeScreen.HomeScreenElements.homeNavigationUpSections.homeNavigationUpSectionPanelControl.StorageNavigationUpOptions
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileNavigationUpSections.profileNavigationUpSectionsPanelControl.StorageProfileNavigationUpOptions
//import gupsmile.com.ui.screens.homeScreen.HomeScreenElements.subScreensHomeScreen.subscreensPanelControl.subscreensManagerState.UiStateHorizontalPagerPage
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.ProfileSbsSnPlCl.profileSubscreensManagerState.UiStateProfileHorizontalPagerPage
//import gupsmile.com.ui.screens.homeScreen.HomeScreenElements.subScreensHomeScreen.subscreensPanelControl.subscreensManagerState.ViewModelHorizontalPagerPage
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.ProfileSbsSnPlCl.profileSubscreensManagerState.ViewModelProfileHorizontalPagerPage
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun ProfileNavigationUpSections(
    modifier: Modifier = Modifier,
    viewModelProfileHorizontalPagerPage: ViewModelProfileHorizontalPagerPage,
    profileHorizontalPagerPageUiState: UiStateProfileHorizontalPagerPage
){
    val itemsSection: List<ProfileNavigationUpOptions> =
        StorageProfileNavigationUpOptions.allValuesStorageProfileNavigationUpOptions

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        item {
            Spacer(modifier = modifier.width(15.dp))
        }
        items(itemsSection){ item ->
            ProfileHomeNavigationUpItem(
                nameSubSection = item.nameSubSection,
                fontFamily =
                if(
                    profileHorizontalPagerPageUiState.stateHorizontalPager.toString() ==
                    item.nameSubSectionId
                )
                    FontFamily(Font(R.font.raleway_bold))
                else
                    FontFamily(Font(R.font.raleway_regular)),

            )
            Spacer(modifier = modifier.width(10.dp))
        }
    }
}



@Composable
fun ProfileHomeNavigationUpItem(
    modifier: Modifier = Modifier,
    @StringRes nameSubSection: Int,
    fontFamily: FontFamily = FontFamily(Font(R.font.raleway_regular)),
){
    Card(
        shape = RoundedCornerShape(7.dp),
        colors = CardDefaults.cardColors(Color.White)

    ) {
        textCommonHomePage(
            modifier = modifier
                .padding( end = 14.dp, top = 13.dp, bottom = 13.dp),
            stringResTextEntry = nameSubSection,
            maxLinesResParameter = 1 ,
            lineHeightParameter = 15.sp,
            fontSizeStyleParameter = 15.sp ,
            fontFamilyStyleParameter = fontFamily,
            colorStyleParameter = Color.Black
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileNavigationUpSectionPreview(){
    GupsMileTheme {
        val viewModelProfileHorizontalPager: ViewModelProfileHorizontalPagerPage = viewModel()
        val profileHorizontalPagerUiState =
            viewModelProfileHorizontalPager.uiState.collectAsState().value
        ProfileNavigationUpSections(
            viewModelProfileHorizontalPagerPage = viewModelProfileHorizontalPager,
            profileHorizontalPagerPageUiState = profileHorizontalPagerUiState
        )
    }
}
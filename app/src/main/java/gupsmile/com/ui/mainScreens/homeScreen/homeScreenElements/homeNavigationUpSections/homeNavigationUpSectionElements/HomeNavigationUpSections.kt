package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeNavigationUpSections.homeNavigationUpSectionElements

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeNavigationUpSections.homeNavigationUpSectionPanelControl.NavigationUpOptions
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeNavigationUpSections.homeNavigationUpSectionPanelControl.StorageNavigationUpOptions
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.UiStateHorizontalPagerPage
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.ViewModelHorizontalPagerPage
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun HomeNavigationUpSections(
    modifier: Modifier = Modifier,
    viewModelHorizontalPager: ViewModelHorizontalPagerPage,
    horizontalPagerUiState: UiStateHorizontalPagerPage
){
    val itemsSection: List<NavigationUpOptions> =
        StorageNavigationUpOptions.allValuesStorageNavigationUpOptions

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            Spacer(modifier = modifier.width(10.dp))
        }
        items(itemsSection){ itemSection ->
            HomeNavigationUpItem(
                nameSubSection = itemSection.nameSubSection,
                fontFamily =
                if(
                    horizontalPagerUiState.stateHorizontalPager.toString() ==
                    itemSection.nameSubSectionId
                )
                    FontFamily(Font(R.font.raleway_medium))
                else
                    FontFamily(Font(R.font.raleway_regular)),
                colorItemSelected =
                if(
                    horizontalPagerUiState.stateHorizontalPager.toString() ==
                    itemSection.nameSubSectionId
                )
                    CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                else
                    CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
                colorFont =
                if(
                    horizontalPagerUiState.stateHorizontalPager.toString() ==
                    itemSection.nameSubSectionId
                ) {
                    MaterialTheme.colorScheme.onPrimary
                } else{
                    MaterialTheme.colorScheme.onPrimaryContainer
                }
            )
            Spacer(modifier = modifier.width(10.dp))
        }
    }
}



@Composable
fun HomeNavigationUpItem(
    modifier: Modifier = Modifier,
    @StringRes nameSubSection: Int,
    fontFamily: FontFamily = FontFamily(Font(R.font.raleway_regular)),
    colorItemSelected: CardColors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
    colorFont: Color = MaterialTheme.colorScheme.onPrimaryContainer
){
    Card(
        shape = RoundedCornerShape(5.dp),
        colors = colorItemSelected

    ) {
        textCommonHomePage(
            modifier = modifier
                .padding(start = 15.dp, end = 15.dp, top = 3.dp, bottom = 3.dp),
            stringResTextEntry = nameSubSection,
            maxLinesResParameter = 1 ,
            lineHeightParameter = 14.sp,
            fontSizeStyleParameter = 14.sp ,
            fontFamilyStyleParameter = fontFamily,
            colorStyleParameter = colorFont
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeNavigationUpSectionPreview(){
    GupsMileTheme {
        val viewModelHorizontalPager: ViewModelHorizontalPagerPage = viewModel()
        val horizontalPagerUiState = viewModelHorizontalPager.uiState.collectAsState().value
        HomeNavigationUpSections(
            viewModelHorizontalPager = viewModelHorizontalPager,
            horizontalPagerUiState = horizontalPagerUiState
        )
    }
}
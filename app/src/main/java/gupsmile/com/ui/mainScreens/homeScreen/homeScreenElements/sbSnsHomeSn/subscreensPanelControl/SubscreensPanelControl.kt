package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.data.temporalConfig.ViewModelGetReviews
import gupsmile.com.data.temporalConfig.ViewModelUrlsImages
//import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeSnMyReviews.HomeScreenMyReviews
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeScreenPost.HomeScreenPostPanelConrtrol
//import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeScreenSaved.HomeScreenSaved
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeScreenSaved.HomeSnSavedSbSn
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeScreenSaved.HomeSnSavedSbSnPlCl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeScreenYourPeople.homeScreenYourPeopleElements.HomeScreenYourPeopleElements
//import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeSnMyReviews.MyReviewsSbSn
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeSnMyReviews.MyReviewsSbSnPlCl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.CurrentPageHorizontalPager
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.PagesHorizontalPagerPage
//import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.InitialPageHorizontalPager
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.ViewModelHorizontalPagerPage
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "RememberReturnType")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SubscreensPanelControl(
    modifier: Modifier = Modifier,
    scrollBehavior : TopAppBarScrollBehavior,
    columnState : ScrollState = rememberScrollState(),
    viewModelUrlImages: ViewModelUrlsImages?,
    viewModelGetReviews: ViewModelGetReviews?,
    navController: NavHostController

    ){
    val viewModelHorizontalPager: ViewModelHorizontalPagerPage = viewModel()
    val horizontalPagerUiState = viewModelHorizontalPager.uiState.collectAsState().value

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {4}
    )

    val selectedTableIndex = remember{
        derivedStateOf { pagerState.currentPage }
    }

    val scope = rememberCoroutineScope()


    viewModelHorizontalPager.updateNamePage(newValue = pagerState.currentPage)


    val pointerAction: () -> Unit = {
        viewModelHorizontalPager.updateStateImage(false)
        viewModelHorizontalPager.updateStateNewGup(false)
        viewModelHorizontalPager.updateStateUpdateGup(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
        ) {
            TabRow(
                selectedTabIndex = selectedTableIndex.value,
                modifier = modifier
                    .fillMaxWidth()
                    .height(45.dp)
            ) {
                PagesHorizontalPagerPage.entries.forEachIndexed{ index, pagesHorizontalPagerPage ->
                    Tab(
                        selected = selectedTableIndex.value == index,
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagesHorizontalPagerPage.ordinal)
                            }
                        },
                        text = {
                            textCommonHomePageString(
                                stringResTextEntry = pagesHorizontalPagerPage.text,
                                maxLinesResParameter = 1 ,
                                lineHeightParameter = 15.sp,
                                fontSizeStyleParameter = 15.sp,
                                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_medium)),
                                colorStyleParameter = Color.Unspecified,
                                modifier = modifier
                                    .clip(RoundedCornerShape(50.dp))
                            )
                        },
                        modifier = modifier
                            .height(30.dp)
                            .padding(bottom = 10.dp)

                    )
                }
            }
            if(horizontalPagerUiState.stateImage || horizontalPagerUiState.stateNewGup || horizontalPagerUiState.stateUpdateGup ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    pointerAction()
                                }
                            )
                        }
                        .matchParentSize()

                )
            }
        }

        HorizontalPager(
            state = pagerState
        ) {page ->

            when(page){
                0 -> { HomeScreenPostPanelConrtrol(
                    columnState = columnState,
                    scrollBehavior = scrollBehavior
                )
                }
                1 -> {  MyReviewsSbSnPlCl(
                    viewModelGetReviews  = viewModelGetReviews,
                    navController = navController,
                )}
                2 -> {  HomeSnSavedSbSnPlCl(
                    viewModelUrlImages = viewModelUrlImages
                )}
                3 -> {HomeScreenYourPeopleElements()}
            }
        }
    }



}
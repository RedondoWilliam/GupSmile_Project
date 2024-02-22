package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ensayo.example.ui.topAppBarHomePage.topBarElements.DefaultAppBar
import gupsmile.com.R
import gupsmile.com.data.temporalConfig.ViewModelGetReviews
import gupsmile.com.data.temporalConfig.ViewModelUrlsImages
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.commonElements.TopAppBarPdTwo
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeNavigationUpSections.homeNavigationUpSectionPanelControl.HomeNavigationUpSectionsPanelControl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeTopAppBar.homeTopAppBarPanelControl.HomeTopAppBarPanelControl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.SubscreensPanelControl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.StatePointerAction
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.StatePointerInputHomeScreen
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.ViewModelHorizontalPagerPage
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.theme.GupsMileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSn(
    modifier: Modifier = Modifier,
    floatingBottomActions: () -> Unit,
    homeTopAppBar: @Composable () -> Unit,
    scroll: TopAppBarScrollBehavior,
    columnState: ScrollState,
    newFloatingBottom: @Composable () -> Unit,
    viewModelUrlImages: ViewModelUrlsImages?,
    viewModelGetReviews: ViewModelGetReviews?,
    navController: NavHostController
){

    val viewModelHorizontalPager: ViewModelHorizontalPagerPage = viewModel()
    val horizontalPagerUiState = viewModelHorizontalPager.uiState.collectAsState().value

    val pointerAction: () -> Unit = {
        viewModelHorizontalPager.updateStateImage(false)
        viewModelHorizontalPager.updateStateNewGup(false)
        viewModelHorizontalPager.updateStateUpdateGup(false)
    }

    Box(
        contentAlignment = Alignment.BottomEnd ,
        modifier = modifier
            .fillMaxSize()

    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )

        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                ) {
                    homeTopAppBar()
//                    HomeNavigationUpSectionsPanelControl()
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
//                Box(
//                    modifier = modifier
//                        .fillMaxWidth()
//                        .matchParentSize()
//                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
//                        .pointerInput(Unit) {
//                            detectTapGestures(
//                                onTap = {
//                                    pointerAction()
//                                }
//                            )
//                        }
//
//                )

            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                SubscreensPanelControl(
                    columnState = columnState,
                    scrollBehavior = scroll,
                    viewModelUrlImages = viewModelUrlImages,
                    viewModelGetReviews = viewModelGetReviews,
                    navController = navController
                )
            }
        }
        newFloatingBottom()
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(46.dp)
                .offset((-10).dp, (-10).dp)
                .clip(CircleShape)

        ) {
            FloatingBottonDesignFixed(
                imageIcon = R.drawable.message,
                coordinateX = (0).dp,
                coordinateY =  (0).dp,
                sizeIcon = 25.dp,
                onclickBottomActions = {
                    floatingBottomActions()
                }
            )
            if(horizontalPagerUiState.stateImage || horizontalPagerUiState.stateNewGup || horizontalPagerUiState.stateUpdateGup ){
                Box(
                    modifier = modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    pointerAction()
                                }
                            )
                        }
                )
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun HomeSnPreview(){
    GupsMileTheme {
        val topAppBarState = rememberTopAppBarState()
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
            state = topAppBarState,
            canScroll = {true},
            snapAnimationSpec = spring(stiffness = Spring.StiffnessVeryLow),
            flingAnimationSpec = rememberSplineBasedDecay()

        )
        HomeSn(
            columnState = rememberScrollState(),
            scroll = scrollBehavior,
            floatingBottomActions = { /*TODO*/ },
            homeTopAppBar = {
//                DefaultAppBar(
//                    scroll = null,
//                    navigationHomeProfile = {},
//                    onLogOutConfirmed = {},
//                    expandedMenuOptions = {},
//                    profileImage = painterResource(id = R.drawable.selfie),
//                    profilePhoto = {
//                        Icon(
//                            painter = painterResource(id = R.drawable.image_add_contact),
//                            contentDescription = "",
//                            tint = MaterialTheme.colorScheme.primary,
//                            modifier = Modifier.size(38.dp)
//                        )
//                    }
//                )
                TopAppBarPdTwo(
                    navigationHomeProfile = {},
                    profilePhoto = {
                        Icon(
                            painter = painterResource(id = R.drawable.image_add_contact),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                )
            },
            newFloatingBottom = {
                FloatingBottonDesignFixed(
                    imageIcon = R.drawable.message,
                    coordinateX = (-10).dp,
                    coordinateY =  (-70).dp,
                    sizeIcon = 25.dp,
                    onclickBottomActions = {
                    }
                )
            },
            viewModelUrlImages = null,
            viewModelGetReviews = null,
            navController =  rememberNavController(),
        )
    }
}
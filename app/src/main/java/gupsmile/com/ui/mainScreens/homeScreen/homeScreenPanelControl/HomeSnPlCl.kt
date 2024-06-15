package gupsmile.com.ui.mainScreens.homeScreen.homeScreenPanelControl

import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
//import androidx.compose.foundation.layout.MinIntrinsicHeightModifier.calculateContentConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ensayo.example.ui.topAppBarHomePage.topBarElements.DefaultAppBar
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.data.temporalConfig.StateNotificationGupAdded
import gupsmile.com.data.temporalConfig.StateTypeOfOperation
//import gupsmile.com.data.temporalConfig.StateAddNewNote
import gupsmile.com.data.temporalConfig.StateUpdateListGups
import gupsmile.com.data.temporalConfig.ViewModelGetReviews
import gupsmile.com.data.temporalConfig.ViewModelUrlsImages
import gupsmile.com.network.isNetworkActive
import gupsmile.com.ui.commonElements.BarProgressPd
import gupsmile.com.ui.commonElements.DialogLoading
import gupsmile.com.ui.commonElements.FloatingBottomDesignPd
//import gupsmile.com.ui.commonElements.DropDownMenuItem
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.HomeSn
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeNavigationUpSections.homeNavigationUpSectionPanelControl.HomeNavigationUpSectionsPanelControl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeTopAppBar.homeTopAppBarPanelControl.HomeTopAppBarPanelControl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeSnMyReviews.NotificationGupAdded
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.SubscreensPanelControl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.PagesHorizontalPagerPage
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.StatePointerAction
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.StatePointerInputHomeScreen
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.ViewModelHorizontalPagerPage
import gupsmile.com.ui.navigationApp.RoutesMainScreens

import gupsmile.com.ui.theme.GupsMileTheme
import gupsmile.com.ui.viewModelPanelControl.viewModelNetwork.NetworkStatusUiState
import gupsmile.com.ui.viewModelPanelControl.viewModelNetwork.StateNetwork
//import gupsmile.com.ui.viewModelPanelControl.viewModelNetwork.StateNetwork
import gupsmile.com.ui.viewModelPanelControl.viewModelNetwork.ViewModelNetwork
import gupsmile.com.ui.viewModelPanelControl.viewModelNetwork.ViewModelStatusNetwork
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPanelControl(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    analytics: AnalitycsManager?,
    auth: AuthManager?,
    viewModelUrlImages: ViewModelUrlsImages,
    viewModelGetReviews: ViewModelGetReviews,
    viewModelNetwork : ViewModelNetwork,
    viewModelStatusNetwork: ViewModelStatusNetwork
){
    val statusNetworkUiState by viewModelStatusNetwork.networkStatusUiState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()

    val viewModelHorizontalPager: ViewModelHorizontalPagerPage = viewModel()
    val horizontalPagerUiState = viewModelHorizontalPager.uiState.collectAsState().value
    val viewModelGetReviewsUiState = viewModelGetReviews.uiState.collectAsState().value

    val viewModelNetworkUiState = viewModelNetwork.uiState.collectAsState().value

    analytics?.logScreenView(screenName = RoutesMainScreens.HomeScreen.route)
    val topAppBarState = rememberTopAppBarState()
    val columnState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState,
        canScroll = {true},
        snapAnimationSpec = spring(stiffness = Spring.StiffnessVeryLow),
        flingAnimationSpec = rememberSplineBasedDecay()

    )
    val floatingBottomActions: () -> Unit = {
        navController.navigate(route = RoutesMainScreens.AddNewHistorySnPlCl.route)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        contentAlignment = Alignment.BottomEnd
    ) {
        HomeSn(
            columnState = columnState,
            scroll = scrollBehavior,
            floatingBottomActions = {
                navController.navigate(route = RoutesMainScreens.MessagesScreen.route)
                                    },
            homeTopAppBar = {
                HomeTopAppBarPanelControl(
                    scroll = scrollBehavior,
                    navController = navController,
                    expandedMenuOptions = {},
                    auth = auth
                )
            },
            newFloatingBottom = {
                AnimatedVisibility(
                    visible = horizontalPagerUiState.stateHorizontalPager == PagesHorizontalPagerPage.SAVED,
                    enter = slideInVertically(),
                    exit = slideOutVertically()
                ) {
                    FloatingBottonDesignFixed(
                        imageIcon = R.drawable.plus,
                        coordinateX = (-10).dp,
                        coordinateY =  (-70).dp,
                        sizeIcon = 18.dp,
                        onclickBottomActions = {
                            floatingBottomActions()
                        }
                    )
                }

                AnimatedVisibility(
                    visible = viewModelGetReviewsUiState.stateUpdateListGups ==StateUpdateListGups.LOADING,
                    enter = fadeIn(),
                    exit = fadeOut()
                )  {
                    FloatingBottomDesignPd(
                        coordinateY = (-10).dp,
                        coordinateX = if(statusNetworkUiState == NetworkStatusUiState.Inactive) (-130).dp else (-70).dp,
                        elementsToShow = {
                            DialogLoading(
                                sizeBox = 44.dp,
                                sizeFigure = 42.dp
                            )
                        }
                    )
                }

                AnimatedVisibility(
                    visible = statusNetworkUiState == NetworkStatusUiState.Inactive,
                    enter = fadeIn(),
                    exit = fadeOut()
                )  {
                    FloatingBottonDesignFixed(
                        imageIcon = R.drawable.error_network_icon,
                        coordinateX =  (-70).dp ,
                        coordinateY = (-10).dp ,
                        sizeIcon = 18.dp,
                        onclickBottomActions = {},
                        colorIcon = MaterialTheme.colorScheme.onErrorContainer,
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                }

                AnimatedVisibility(
                    visible = viewModelGetReviewsUiState.stateNotificationGupAdded == StateNotificationGupAdded.SUCCESS,
                    enter = slideInHorizontally(),
                    exit = slideOutHorizontally()
                ) {
                    Box(
                        modifier
                            .align(Alignment.BottomStart)
                            .offset(
                                y = ((-70).dp)
                            )
                    ) {
                        NotificationGupAdded(
                            contentBottomNotification = R.string.notification_gup_added
                        )
                        LaunchedEffect(Unit){
                            delay(1500)
                            viewModelGetReviews.updateStateNotificationGupAdded(
                                StateNotificationGupAdded.UNDEFINE
                            )
                            viewModelGetReviews.updateStateTypeOfOperation(StateTypeOfOperation.UNDEFINE)
                        }
                    }
                }
                AnimatedVisibility(
                    visible = viewModelGetReviewsUiState.stateNotificationGupAdded == StateNotificationGupAdded.FAILED,
                    enter = slideInHorizontally(),
                    exit = slideOutHorizontally()
                ) {
                    Box(
                        modifier
                            .align(Alignment.BottomStart)
                            .offset(
                                y = ((-70).dp)
                            )
                    ) {
                        NotificationGupAdded(
                             contentBottomNotification =  R.string.notification_gup_added_failed
                        )
                        LaunchedEffect(Unit){
                            delay(1500)
                            viewModelGetReviews.updateStateNotificationGupAdded(
                                StateNotificationGupAdded.UNDEFINE
                            )

                        }
                    }
                }


            },
            viewModelUrlImages = viewModelUrlImages,
            viewModelGetReviews = viewModelGetReviews,
            navController = navController
        )

    }

}


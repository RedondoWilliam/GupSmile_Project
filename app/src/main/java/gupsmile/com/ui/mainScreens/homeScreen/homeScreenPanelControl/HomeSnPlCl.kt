package gupsmile.com.ui.mainScreens.homeScreen.homeScreenPanelControl

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
//import androidx.compose.foundation.layout.MinIntrinsicHeightModifier.calculateContentConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
//import gupsmile.com.ui.commonElements.DropDownMenuItem
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeNavigationUpSections.homeNavigationUpSectionPanelControl.HomeNavigationUpSectionsPanelControl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeSubTopBarOptions.homeSubTopBarOptionsPanelControl.HomeSubTopBarOptionsPanelControl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeTopAppBar.homeTopAppBarPanelControl.HomeTopAppBarPanelControl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.subScreensHomeScreen.subscreensPanelControl.SubscreensPanelControl
import gupsmile.com.ui.navigationApp.RoutesMainScreens

import gupsmile.com.ui.theme.GupsMileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPanelControl(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    analytics: AnalitycsManager?,
    auth: AuthManager?
){

    analytics?.logScreenView(screenName = RoutesMainScreens.HomeScreen.route)
    val topAppBarState = rememberTopAppBarState()
    val columnState = rememberScrollState()


    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState,
        canScroll = {true},
        snapAnimationSpec = spring(stiffness = Spring.StiffnessVeryLow),
        flingAnimationSpec = rememberSplineBasedDecay()

    )

    Box(
        contentAlignment = Alignment.BottomEnd ,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            HomeTopAppBarPanelControl(
                scroll = scrollBehavior,
                navController = navController,
                expandedMenuOptions = {},
                auth = auth
            )
            HomeSubTopBarOptionsPanelControl(
                auth = auth
            )
            Spacer(modifier = modifier.height(10.dp))
            HomeNavigationUpSectionsPanelControl()
            Spacer(modifier = modifier.height(5.dp))
            SubscreensPanelControl(
                columnState = columnState,
                scrollBehavior = scrollBehavior
            )

        }
        FloatingBottonDesignFixed(
            imageIcon = R.drawable.plus,
            coordinateX = (-10).dp,
            coordinateY =  (-10).dp,
            sizeIcon = 20.dp
        )


    }
}


@Composable
@Preview(showBackground = true, device = "spec:width=1080px,height=2340px,dpi=440")
fun HomeScreenPreview(
){
    GupsMileTheme {
        HomeScreenPanelControl(
            analytics = null,
            auth = null
        )
    }
}
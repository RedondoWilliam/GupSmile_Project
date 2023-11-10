package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ensayo.example.ui.topAppBarHomePage.topBarElements.DefaultAppBar
import gupsmile.com.R
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeNavigationUpSections.homeNavigationUpSectionPanelControl.HomeNavigationUpSectionsPanelControl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeTopAppBar.homeTopAppBarPanelControl.HomeTopAppBarPanelControl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.SubscreensPanelControl
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.theme.GupsMileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSn(
    modifier: Modifier = Modifier,
    floatingBottomActions: () -> Unit,
    homeTopAppBar: @Composable () -> Unit,
    scroll: TopAppBarScrollBehavior,
    columnState: ScrollState
){

    Box(
        contentAlignment = Alignment.BottomEnd ,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.background
                )
        ) {
            homeTopAppBar()
            Spacer(modifier = modifier.height(10.dp))
            HomeNavigationUpSectionsPanelControl()
            Spacer(modifier = modifier.height(5.dp))
            SubscreensPanelControl(
                columnState = columnState,
                scrollBehavior = scroll
            )
        }
        FloatingBottonDesignFixed(
            imageIcon = R.drawable.message,
            coordinateX = (-10).dp,
            coordinateY =  (-10).dp,
            sizeIcon = 25.dp,
            onclickBottomActions = {
                floatingBottomActions()
            }
        )


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
                DefaultAppBar(
                    scroll = null,
                    navigationHomeProfile = {},
                    onLogOutConfirmed = {},
                    expandedMenuOptions = {},
                    profileImage = painterResource(id = R.drawable.selfie),
                    profilePhoto = {
                        Icon(
                            painter = painterResource(id = R.drawable.image_add_contact),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                )
            }
        )
    }
}
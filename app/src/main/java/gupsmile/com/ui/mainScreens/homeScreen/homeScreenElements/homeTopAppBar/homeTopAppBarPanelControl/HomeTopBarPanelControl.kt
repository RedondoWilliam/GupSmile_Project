package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeTopAppBar.homeTopAppBarPanelControl

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ensayo.example.ui.topAppBarHomePage.topBarElements.TopAppBarBody
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.ui.navigationApp.RoutesMainScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBarPanelControl(
    modifier: Modifier = Modifier,
    scroll: TopAppBarScrollBehavior?,
    navController: NavHostController = rememberNavController(),
    expandedMenuOptions: () -> Unit,
    auth: AuthManager?
){
    val user = auth?.getCurrentUser()

    val profileImage =
        if(user?.photoUrl != null){
            painterResource(id = R.drawable.profile_image)
        }else{
            painterResource(id = R.drawable.profile_image)
        }

    TopAppBarBody(
        onSearchClicked = {  },
        scroll = scroll,
        navigationHomeProfile = {
            navController.navigate(RoutesMainScreens.ProfileScreen.route)
        },
        onLogOutConfirmed = {},
        expandedMenuOptions = expandedMenuOptions,
        profileImage = profileImage
    )
}
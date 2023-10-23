package gupsmile.com.ui.navigationApp

import android.content.Context
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseUser
import gupsmile.com.data.MyModule
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenPanelControl.HomeScreenPanelControl
//import gupsmile.com.ui.mainScreens.homeScreen.homeScreenPanelControl.HomeScreenPanelControl
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenPanelControl.LoginScreenPanelControl
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenPanelControl.ProfileScreenPanelControl
import gupsmile.com.ui.settingScreens.registerNewUserScreen.registerNewUserScreenPanelControl.RegisterNewUserScreenPanelControl
import gupsmile.com.ui.settingScreens.retrievePasswordScreen.retrievePasswordScreenPanelControl.RetrievePasswordScreenPanelControl
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateCurrentUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication


sealed class RoutesMainScreens(val route:String){
    object HomeScreen: RoutesMainScreens("homeScreen")
    object LoginScreen: RoutesMainScreens("loginScreen")
    object ProfileScreen: RoutesMainScreens("profileScreen")
    object RetrieveScreen: RoutesMainScreens("retrieveScreen")
    object CreateNewAccount: RoutesMainScreens("createNewAccount")
}



@Composable
fun NavigationMainScreens(
    modifier: Modifier = Modifier,
    context: Context,
    viewModelAuthentication: ViewModelAuthentication
){

    val authManager: AuthManager = AuthManager()
    var analytics = AnalitycsManager(context)
//    val viewModelAuthentication : ViewModelAuthentication = viewModel()
    val authenticationUiState = viewModelAuthentication.uiState.collectAsState().value
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination =
        if( authenticationUiState.stateCurrentUser == StateCurrentUser.NULE)
            RoutesMainScreens.LoginScreen.route
        else
            RoutesMainScreens.HomeScreen.route,
    ){
        composable(route = RoutesMainScreens.LoginScreen.route){
            LoginScreenPanelControl(
                navController = navController,
                analytics = analytics,
                auth = authManager,
                viewModelAuthentication = viewModelAuthentication
            )
        }
        composable(route = RoutesMainScreens.HomeScreen.route){
            HomeScreenPanelControl(
                navController = navController,
                analytics = analytics,
                auth = authManager
            )
        }
        composable(route = RoutesMainScreens.ProfileScreen.route){
            ProfileScreenPanelControl(
                analytics = analytics,
                auth = authManager,
                navController = navController,
                viewModelAuthentication = viewModelAuthentication
            )
        }
        composable(route = RoutesMainScreens.RetrieveScreen.route){
            RetrievePasswordScreenPanelControl(
                analytics = analytics,
                navController = navController,
                auth = authManager,
                viewModelAuthentication = viewModelAuthentication
            )
        }
        composable(route = RoutesMainScreens.CreateNewAccount.route){
            RegisterNewUserScreenPanelControl(
                analytics = analytics,
                navController = navController,
                auth = authManager,
                viewModelAuthentication = viewModelAuthentication
            )
        }
    }

}


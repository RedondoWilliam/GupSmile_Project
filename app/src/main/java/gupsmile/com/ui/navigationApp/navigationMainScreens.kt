package gupsmile.com.ui.navigationApp

import android.content.Context
import androidx.activity.viewModels
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import gupsmile.com.data.MyModuleAnalytics
import gupsmile.com.data.MyModuleAuthentication
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.data.temporalConfig.ViewModelGetReviews
import gupsmile.com.data.temporalConfig.ViewModelUrlsImages
import gupsmile.com.data.testWoMa.viewModelShowMessage.ViewModelShowMessage
import gupsmile.com.ui.mainScreens.contactsSn.ContactsSnPlCl.ContactsSnPlCl
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenPanelControl.HomeScreenPanelControl
//import gupsmile.com.ui.mainScreens.homeScreen.homeScreenPanelControl.HomeScreenPanelControl
//import gupsmile.com.ui.mainScreens.loginScreen.loginScreenPanelControl.LoginScreenPanelControl
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenPanelControl.LoginSnPlCl
import gupsmile.com.ui.mainScreens.messagesScreen.messagesSnPlCl.MessagesSnPlCl
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenPanelControl.ProfileScreenPanelControl
import gupsmile.com.ui.settingScreens.addContactLocalSn.addLocalContactPlCl.AddContactLocalSnPlCl
//import gupsmile.com.ui.settingScreens.addNewGupSn.AddNewGupSnPlCl
import gupsmile.com.ui.settingScreens.addNewResourceSn.AddNewHistorySnPlCl
import gupsmile.com.ui.settingScreens.registerNewUserScreen.registerNewUserScreenPanelControl.RegisterNewUserScreenPanelControl
import gupsmile.com.ui.settingScreens.retrievePasswordScreen.retrievePasswordScreenPanelControl.RetrievePasswordScreenPanelControl
import gupsmile.com.ui.subScreens.ChatScreen.ChatScreenPanelControl
import gupsmile.com.ui.subScreens.infoLocalContactSn.infoLocalContactPlCl.InfoLocalContactPlCl
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateCurrentUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication
import gupsmile.com.ui.viewModelPanelControl.viewModelNetwork.ViewModelNetwork
import gupsmile.com.ui.viewModelPanelControl.viewModelNetwork.ViewModelStatusNetwork


sealed class RoutesMainScreens(val route:String){
    object HomeScreen: RoutesMainScreens("homeScreen")
    object LoginScreen: RoutesMainScreens("loginScreen")
    object ProfileScreen: RoutesMainScreens("profileScreen")
    object RetrieveScreen: RoutesMainScreens("retrieveScreen")
    object CreateNewAccount: RoutesMainScreens("createNewAccount")
    object MessagesScreen: RoutesMainScreens("messagesScreen")
    object ContactsScreen: RoutesMainScreens("contactsScreen")
    object AddContactLocalSnPlCl: RoutesMainScreens("addContactLocalSnPlCl")

    object InfoLocalContactPlCl: RoutesMainScreens("infoLocalContactPlCl")
    object AddNewHistorySnPlCl: RoutesMainScreens("addNewHistorySnPlCl")
    object ChatScreen: RoutesMainScreens("chatScreen")
}



@Composable
fun NavigationMainScreens(
    modifier: Modifier = Modifier,
    context: Context,
    viewModelAuthentication: ViewModelAuthentication,
    viewModelUrlsImages: ViewModelUrlsImages,
    viewModelGetReviews: ViewModelGetReviews,
    viewModelNetwork: ViewModelNetwork,
    viewModelStatusNetwork: ViewModelStatusNetwork,
    viewModelShowMessage: ViewModelShowMessage
){

    val authManager: AuthManager = MyModuleAuthentication.providesAutheticationManagerInstance(context)
    var analytics = MyModuleAnalytics.provideAnalyticsManagerInstance(context)
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
            LoginSnPlCl(
                navController = navController,
                analytics = analytics,
                auth = authManager,
                viewModelAuthentication = viewModelAuthentication
            )
        }
        composable(route = RoutesMainScreens.HomeScreen.route){

            val viewModelShowMessagee: ViewModelShowMessage = hiltViewModel()

            HomeScreenPanelControl(
                navController = navController,
                analytics = analytics,
                auth = authManager,
                viewModelUrlImages = viewModelUrlsImages,
                viewModelGetReviews = viewModelGetReviews,
                viewModelNetwork = viewModelNetwork,
                viewModelStatusNetwork = viewModelStatusNetwork,
                viewModelShowMessage = viewModelShowMessagee
            )
        }
        composable(route = RoutesMainScreens.ProfileScreen.route){

            val viewModelShowMessagee: ViewModelShowMessage = hiltViewModel()

            ProfileScreenPanelControl(
                analytics = analytics,
                auth = authManager,
                navController = navController,
                viewModelAuthentication = viewModelAuthentication,
                viewModelGetReviews = viewModelGetReviews,
                viewModelShowMessage = viewModelShowMessagee
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
        composable(route = RoutesMainScreens.MessagesScreen.route){
           MessagesSnPlCl(
               navController = navController,
           )
        }
        composable(
            route = RoutesMainScreens.ContactsScreen.route ,
        ){
            ContactsSnPlCl(
                navController = navController,
                navigateToAddContact = {
                    navController.navigate(route = RoutesMainScreens.AddContactLocalSnPlCl.route + "/{contactKey}")
                }
            )
        }
        composable(
            route = RoutesMainScreens.AddContactLocalSnPlCl.route + "/{contactKey}",
            arguments = listOf(navArgument("contactKey"){type = NavType.StringType})
        ){
            AddContactLocalSnPlCl(
                navController = navController,
                contactKey =  it.arguments?.getString("contactKey")
            )
        }
        composable(
            route = RoutesMainScreens.InfoLocalContactPlCl.route + "/{contactKey_edit_contact}",
            arguments = listOf(navArgument("contactKey_edit_contact"){type = NavType.StringType})
        ){
            InfoLocalContactPlCl(
                navController = navController,
                contactKey =  it.arguments?.getString("contactKey_edit_contact")
            )
        }

        composable(
            route = RoutesMainScreens.AddNewHistorySnPlCl.route
        ){
            AddNewHistorySnPlCl(
                navController = navController
            )
        }

        composable(
            route = RoutesMainScreens.ChatScreen.route
        ){
            ChatScreenPanelControl()
        }


    }

}


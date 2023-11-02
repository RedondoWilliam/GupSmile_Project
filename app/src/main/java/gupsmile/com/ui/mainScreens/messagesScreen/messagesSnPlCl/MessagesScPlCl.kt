package gupsmile.com.ui.mainScreens.messagesScreen.messagesSnPlCl

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import gupsmile.com.ui.mainScreens.messagesScreen.messagesSnEs.HomeMessagesSn
import gupsmile.com.ui.navigationApp.RoutesMainScreens

@Composable
fun MessagesSnPlCl(
    modifier: Modifier = Modifier,
    navController: NavHostController
){

    BackHandler {
        navController.navigate(route = RoutesMainScreens.HomeScreen.route){
            popUpTo(route = RoutesMainScreens.MessagesScreen.route){
                inclusive = false
            }
        }
    }
    HomeMessagesSn(
        arrowBackTopBottom = {
            navController.navigate(route = RoutesMainScreens.HomeScreen.route){
                popUpTo(route = RoutesMainScreens.MessagesScreen.route){
                    inclusive = false
                }
            }
        },
        bottomContactActions = {
            navController.navigate(route = RoutesMainScreens.ContactsScreen.route)
        }
    )
}
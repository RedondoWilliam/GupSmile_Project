package gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.forgotMyPasswordScn

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.ui.commonElements.TitleSectionTextPd
import gupsmile.com.ui.navigationApp.RoutesMainScreens

@Composable
fun ForgotMyPasswordScnPlCl(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    analytics: AnalitycsManager?,
){
    TitleSectionTextPd(
        bottonAction = {
            navController.navigate(route = RoutesMainScreens.RetrieveScreen.route)
            analytics?.logButtonClicked("Click: ¿Olvidaste tu contraseña?")
        },
        titleSectionText = R.string.forgot_my_password
    )
}
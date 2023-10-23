package gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.forgotMyPasswordSn

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.ui.navigationApp.RoutesMainScreens

@Composable
fun ForgotMyPasswordSection(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    analytics: AnalitycsManager?,
){
    val bottonAction = {
        navController.navigate(route = RoutesMainScreens.RetrieveScreen.route)
    }
    textCommonHomePage(
        stringResTextEntry = R.string.forgot_my_password,
        maxLinesResParameter = 1,
        lineHeightParameter = 13.sp,
        fontSizeStyleParameter = 13.sp,
        fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_extra_light_italic)),
        colorStyleParameter = colorResource(id = R.color.color_fotgot_password),
        modifier = modifier
            .clickable {
                bottonAction()
                analytics?.logButtonClicked("Click: ¿Olvidaste tu contraseña?")
            }
    )
}
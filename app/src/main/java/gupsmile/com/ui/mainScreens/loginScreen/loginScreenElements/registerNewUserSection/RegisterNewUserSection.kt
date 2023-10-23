package gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.registerNewUserSection

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
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
import gupsmile.com.ui.navigationApp.RoutesMainScreens

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RegiterNewUserSection(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){

     val actionBottom = {
         navController.navigate(route = RoutesMainScreens.CreateNewAccount.route)
     }

    Row (
        modifier = modifier
            .clickable {
                actionBottom()
            }
    ){
        textCommonHomePage(
            stringResTextEntry = R.string.register_new_user,
            maxLinesResParameter = 1,
            lineHeightParameter = 13.sp,
            fontSizeStyleParameter = 13.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_extra_light_italic)),
            colorStyleParameter = colorResource(id = R.color.color_fotgot_password),
        )
    }
}
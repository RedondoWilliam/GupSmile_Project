package gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.registerNewUserSection

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.commonElements.TitleSectionTextPd
import gupsmile.com.ui.navigationApp.RoutesMainScreens

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RegiterNewUserScnPlCl(
    modifier: Modifier = Modifier,
    navController: NavHostController,
){
    TitleSectionTextPd(
        bottonAction = { navController.navigate(route = RoutesMainScreens.CreateNewAccount.route) },
        titleSectionText = R.string.register_new_user
    )
}
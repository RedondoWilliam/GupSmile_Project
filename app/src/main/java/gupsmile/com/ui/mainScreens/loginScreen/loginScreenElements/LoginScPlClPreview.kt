package gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.commonElements.TitleSectionTextPd
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.forgotMyPasswordScn.ForgotMyPasswordScnPlCl
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginAsVisitorScn.LoginAsVisitorScn
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginAsVisitorScn.LoginAsVisitorScnPlCl
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithGoogleScn.LoginWithGoogleScn
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithGoogleScn.LoginWithGoogleScnPlCl
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithPhoneOrEmailSn.loginWithPhoneOrEmailSnEs.LoginWithPhoneOrEmailScn
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.registerNewUserSection.RegiterNewUserScnPlCl
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
@Preview(showBackground = true)
fun LoginSnPlClPreview(){
    GupsMileTheme {
        val columnStateLogin = rememberScrollState()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 30.dp, bottom = 30.dp)
                    .verticalScroll(columnStateLogin),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                textCommonHomePage(
                    stringResTextEntry = R.string.title_app_login,
                    maxLinesResParameter = 1,
                    lineHeightParameter = 30.sp,
                    fontSizeStyleParameter = 30.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_bold)),
                    colorStyleParameter = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(132.dp))

                LoginWithPhoneOrEmailScn(
                    email = "",
                    password = "",
                    onTextChangeEmail = {},
                    onTextChangePassword = {},
                    bottomActions = {}
                )
                Spacer(modifier = Modifier.height(26.dp))
                TitleSectionTextPd(
                    bottonAction = {},
                    titleSectionText = R.string.forgot_my_password
                )
                Spacer(modifier = Modifier.height(23.dp))
                TitleSectionTextPd(
                    bottonAction = {},
                    titleSectionText = R.string.register_new_user
                )
                Spacer(modifier = Modifier.height(57.dp))
                LoginWithGoogleScn(
                    buttonAction = {}
                )
                Spacer(modifier = Modifier.height(26.dp))
                LoginAsVisitorScn()
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginSnPlClDarkModePreview(){
    GupsMileTheme(
        darkTheme = true
    ) {
        val columnStateLogin = rememberScrollState()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 30.dp, bottom = 30.dp)
                    .verticalScroll(columnStateLogin),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                textCommonHomePage(
                    stringResTextEntry = R.string.title_app_login,
                    maxLinesResParameter = 1,
                    lineHeightParameter = 30.sp,
                    fontSizeStyleParameter = 30.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_bold)),
                    colorStyleParameter = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(132.dp))

                LoginWithPhoneOrEmailScn(
                    email = "",
                    password = "",
                    onTextChangeEmail = {},
                    onTextChangePassword = {},
                    bottomActions = {}
                )
                Spacer(modifier = Modifier.height(26.dp))
                TitleSectionTextPd(
                    bottonAction = {},
                    titleSectionText = R.string.forgot_my_password
                )
                Spacer(modifier = Modifier.height(23.dp))
                TitleSectionTextPd(
                    bottonAction = {},
                    titleSectionText = R.string.register_new_user
                )
                Spacer(modifier = Modifier.height(57.dp))
                LoginWithGoogleScn(
                    buttonAction = {}
                )
                Spacer(modifier = Modifier.height(26.dp))
                LoginAsVisitorScn()
            }

        }
    }
}
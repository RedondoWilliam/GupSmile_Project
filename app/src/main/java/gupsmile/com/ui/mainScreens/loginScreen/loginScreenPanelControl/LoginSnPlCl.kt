package gupsmile.com.ui.mainScreens.loginScreen.loginScreenPanelControl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.ui.commonElements.DialogLoading
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.forgotMyPasswordScn.ForgotMyPasswordScnPlCl
//import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.forgotMyPasswordSn.ForgotMyPasswordSection
//import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginAsVisitorScn.LoginAsVisitorPanelControl
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginAsVisitorScn.LoginAsVisitorScnPlCl
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithGoogleScn.LoginWithGoogleScnPlCl
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithPhoneOrEmailSn.loginWithPhoneOrEmailSnPlCl.LoginWithPhoneOrEmailScnPlCl
//import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithGoogleScn.LoginWithGoogleSnPlCl
//import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithPhoneOrEmailSection.loginWithPhoneOrEmailSectionElements.LoginWithPhoneOrEmailSectionPanelControl
//import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithPhoneOrEmailSn.loginWithPhoneOrEmailSnPlCl.LoginWithPhoneOrEmailSectionPanelControl
import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.registerNewUserSection.RegiterNewUserScnPlCl
//import gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.registerNewUserSection.RegiterNewUserSection
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateLoginAsVisitor
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateLoginWithGoogle
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateSignInUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication

@Composable
fun LoginSnPlCl(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    analytics: AnalitycsManager,
    auth: AuthManager,
    viewModelAuthentication: ViewModelAuthentication
){
    analytics.logScreenView(screenName = RoutesMainScreens.LoginScreen.route)
    val authenticationUiState = viewModelAuthentication.uiState.collectAsState().value

    val columnStateLogin = rememberScrollState()
   Box(
       modifier = modifier
           .fillMaxSize()
           .navigationBarsPadding()
           .statusBarsPadding(),
       contentAlignment = Alignment.Center
   ) {
       Column(
           modifier = modifier
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
           Spacer(modifier = modifier.height(132.dp))

           LoginWithPhoneOrEmailScnPlCl(
               analytics = analytics,
               navController = navController,
               auth = auth,
               viewModelAuthentication = viewModelAuthentication
           )
           Spacer(modifier = modifier.height(26.dp))
           ForgotMyPasswordScnPlCl(
               navController = navController,
               analytics = analytics
           )
           Spacer(modifier = modifier.height(23.dp))
           RegiterNewUserScnPlCl(
               navController = navController
           )
           Spacer(modifier = modifier.height(57.dp))
           LoginWithGoogleScnPlCl(
               analytics = analytics,
               navController = navController,
               viewModelAuthentication =viewModelAuthentication
           )
           Spacer(modifier = modifier.height(26.dp))
           LoginAsVisitorScnPlCl(
               auth = auth,
               analytics = analytics,
               navController = navController,
               viewModelAuthentication = viewModelAuthentication
           )
       }

       if(
           authenticationUiState.stateSignInUser == StateSignInUser.LOADING ||
           authenticationUiState.stateLoginAsVisitor == StateLoginAsVisitor.LOADING ||
           authenticationUiState.stateLoginWithGoogle == StateLoginWithGoogle.LOADING
           ){
           DialogLoading()
       }
   }

}




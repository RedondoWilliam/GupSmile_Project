package gupsmile.com.ui.settingScreens.registerNewUserScreen.registerNewUserScreenElements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.ui.commonElements.BottomItemDesignFixed
import gupsmile.com.ui.commonElements.EmailTextEntryRegisterNewUserScreen
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun RegisterNewUserScreen(
    modifier: Modifier = Modifier,
    bottonActions: () -> Unit,
    arrowBackTopBottom: () -> Unit,
    existAccountActions: () -> Unit,
    auth: AuthManager?,
    email: String,
    password: String,
    confirmPassword : String,
    ontextChangeEmail: (String) -> Unit,
    onTextChangePassword: (String) -> Unit,
    onTextConfirmPassword: (String) -> Unit
){

    val columnStateRegisterNewUser = rememberScrollState()
    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 30.dp, bottom = 30.dp)
                .verticalScroll(columnStateRegisterNewUser),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            textCommonHomePage(
                stringResTextEntry = R.string.title_register_new_user,
                maxLinesResParameter = 1,
                lineHeightParameter = 17.sp,
                fontSizeStyleParameter = 17.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_medium)),
                colorStyleParameter = Color.Black
            )
            Spacer(modifier = modifier.height(75.dp))
            textCommonHomePage(
                stringResTextEntry = R.string.email_register_new_user,
                maxLinesResParameter = 1,
                lineHeightParameter = 15.sp,
                fontSizeStyleParameter = 15.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                colorStyleParameter = Color.Black,
                modifier = modifier
                    .padding(start = 25.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = modifier.height(22.dp))
            EmailTextEntryRegisterNewUserScreen(
                textPassword = email,
                ontextchage = {ontextChangeEmail(it)},
                onCloseClicked = { /*TODO*/ },
                onSearchClicked = {},
                focusRequester = FocusRequester(),
            )
            Spacer(modifier = modifier.height(22.dp))
            textCommonHomePage(
                stringResTextEntry = R.string.password_register_new_user,
                maxLinesResParameter = 1,
                lineHeightParameter = 15.sp,
                fontSizeStyleParameter = 15.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                colorStyleParameter = Color.Black,
                modifier = modifier
                    .padding(start = 25.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = modifier.height(22.dp))
            PasswordTextEntryRegisterNewUserScreen(
                textPassword = password,
                ontextchage = {onTextChangePassword(it)},
                onCloseClicked = { /*TODO*/ },
                onSearchClicked = {},
                focusRequester = FocusRequester()
            )
            Spacer(modifier = modifier.height(22.dp))
            PasswordConfirmTextEntryRegisterNewUser(
                textPassword = confirmPassword,
                ontextchage = {onTextConfirmPassword(it)},
                onCloseClicked = { /*TODO*/ },
                onSearchClicked = {},
                focusRequester = FocusRequester()
            )
            Spacer(modifier = modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                BottomItemDesignFixed(
                    bottomActions = {
                        bottonActions()
                                    },
                    padddingStart = 5.dp,
                    paddingEnd = 5.dp,
                    widthFixed = 176.dp,
                    textBottonName = R.string.create_account_bottom_register_new_user,
                    sizeFontBottom = 15.sp
                )
            }
            Spacer(modifier = modifier.height(47.dp))
            Row(
                modifier = modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .clickable { existAccountActions() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                textCommonHomePage(
                    stringResTextEntry = R.string.exist_account_login_register_new_user,
                    maxLinesResParameter = 1,
                    lineHeightParameter = 15.sp,
                    fontSizeStyleParameter = 15.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_extra_light_italic)),
                    colorStyleParameter = colorResource(id = R.color.color_fotgot_password)
                )
            }

        }
        FloatingBottonDesignFixed(
            imageIcon = R.drawable.arrowback,
            coordinateX = 15.dp,
            coordinateY = 20.dp,
            sizeIcon = 20.dp,
            onclickBottomActions = {
                arrowBackTopBottom()
            }
        )
    }
}


@Composable
@Preview(showBackground = true)
fun RegisterNewUserScreenPreview(){
    GupsMileTheme {
        RegisterNewUserScreen(
            bottonActions = {},
            arrowBackTopBottom = {},
            existAccountActions = {},
            auth = null,
            email =  "",
            password = "",
            confirmPassword = "",
            ontextChangeEmail = {},
            onTextChangePassword = {},
            onTextConfirmPassword = {}
        )
    }
}

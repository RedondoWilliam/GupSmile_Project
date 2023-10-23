package gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithPhoneOrEmailSn.loginWithPhoneOrEmailSnEs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gupsmile.com.R
import gupsmile.com.ui.commonElements.BottomItemDesignFixed
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun LoginWithPhoneOrEmailSection(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    onTextChangeEmail: (String)-> Unit,
    onTextChangePassword: (String) -> Unit,
    bottomActions: () -> Unit

){

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        PhoneOrEmailTextEntry(
            textUser = email,
            ontextchage = {onTextChangeEmail(it)} ,
            onCloseClicked = { /*TODO*/ },
            onSearchClicked = {} ,
            focusRequester = FocusRequester()
        )
        Spacer(modifier = modifier.height(20.dp))
        PasswordTextEntry(
            textPassword = password,
            ontextchage = {onTextChangePassword(it)} ,
            onCloseClicked = { /*TODO*/ },
            onSearchClicked = {} ,
            focusRequester = FocusRequester()
        )
        Spacer(modifier = modifier.height(33.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            BottomItemDesignFixed(
                bottomActions = {bottomActions()},
                padddingStart = 5.dp,
                paddingEnd = 5.dp,
                widthFixed = 127.dp ,
                textBottonName = R.string.login_bottom
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun LoginWithPhoneOrEmailSectionPreview(){
    GupsMileTheme {
        LoginWithPhoneOrEmailSection(
            email = "",
            password = "",
            onTextChangeEmail = {},
            onTextChangePassword = {},
            bottomActions = {}
        )
    }
}
package gupsmile.com.ui.settingScreens.retrievePasswordScreen.retrievePasswordScreenElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.commonElements.BottomItemDesignFixed
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.commonElements.EmailTextEntryRegisterNewUserScreen
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun RetrievePasswordScreen(
    modifier: Modifier = Modifier,
    bottomAction: () -> Unit = {},
    arrowBackBottom: () -> Unit = {},
    onTextChange: (String) -> Unit,
    email: String
){

    val columnStateRegisterNewUser = rememberScrollState()
    var focusRequester: FocusRequester = remember{FocusRequester()}
    val focusManager = LocalFocusManager.current

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
                stringResTextEntry = R.string.title_retrieve_password,
                maxLinesResParameter = 1,
                lineHeightParameter = 17.sp,
                fontSizeStyleParameter = 17.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_medium)),
                colorStyleParameter = Color.Black
            )
            Spacer(modifier = modifier.height(113.dp))
            textCommonHomePage(
                stringResTextEntry = R.string.subtitle_retrieve_password,
                maxLinesResParameter = 1,
                lineHeightParameter = 15.sp,
                fontSizeStyleParameter = 15.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                colorStyleParameter = Color.Black,
            )
            Spacer(modifier = modifier.height(22.dp))
            EmailTextEntryRegisterNewUserScreen(
                textPassword = email,
                ontextchage = { onTextChange(it) },
                onCloseClicked = { /*TODO*/ },
                onSearchClicked = {},
                focusRequester = focusRequester,
            )
            Spacer(modifier = modifier.height(22.dp))

            Spacer(modifier = modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                BottomItemDesignFixed(
                    bottomActions = {
                        focusManager.clearFocus()
                        bottomAction()
                                    },
                    padddingStart = 5.dp,
                    paddingEnd = 5.dp,
                    widthFixed = 176.dp,
                    textBottonName = R.string.send_email_bottom_retrieve_password,
                    sizeFontBottom = 15.sp
                )
            }

        }
        FloatingBottonDesignFixed(
            imageIcon = R.drawable.arrowback,
            coordinateX = 15.dp,
            coordinateY = 20.dp,
            sizeIcon = 20.dp,
            onclickBottomActions = {arrowBackBottom()}
        )
    }
}


@Composable
@Preview(showBackground = true)
fun RetrievePasswordScreenPreview(){
    GupsMileTheme {
        RetrievePasswordScreen(
            bottomAction = {},
            onTextChange = {},
            email = ""
        )
    }
}

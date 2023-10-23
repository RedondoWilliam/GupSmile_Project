package gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithPhoneOrEmailSn.loginWithPhoneOrEmailSnEs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun LoginBottom(
    modifier: Modifier = Modifier,
    navigateHomeScreen: () -> Unit,
){
    Row(
        modifier = modifier
            .padding(start = 114.dp, end = 114.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.Black)
            .width(127.dp)
            .height(32.dp)
            .clickable { navigateHomeScreen() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        textCommonHomePage(
            stringResTextEntry = R.string.login_bottom,
            maxLinesResParameter = 1,
            lineHeightParameter = 13.sp,
            fontSizeStyleParameter = 13.sp,
            fontFamilyStyleParameter =FontFamily(Font(R.font.raleway_semi_bold)),
            colorStyleParameter = Color.White
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoginBottomPreview(){
    GupsMileTheme {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            LoginBottom(
                navigateHomeScreen = {}
            )
        }
    }
}
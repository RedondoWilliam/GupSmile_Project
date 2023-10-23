package gupsmile.com.ui.mainScreens.loginScreen.loginScreenElements.loginWithGoogleSn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.R.drawable.icon_google
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun LoginWithGoogleSection(
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .width(280.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(colorResource(id = R.color.color_botton_login_google))
            .padding(start = 30.dp, end = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = ImageVector.Companion.vectorResource(
                icon_google
            ),
            contentDescription = "Search Icon",
            tint = Color.Unspecified,
            modifier = Modifier
                .size(17.dp)
        )

        Spacer(modifier = modifier.width(15.dp))
        textCommonHomePage(
            stringResTextEntry = R.string.login_google_bottom,
            maxLinesResParameter = 1,
            lineHeightParameter = 13.sp,
            fontSizeStyleParameter = 13.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_medium)),
            colorStyleParameter = Color.Black
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoginGoogleSectionPreview(){
    GupsMileTheme {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginWithGoogleSection()
        }
    }
}
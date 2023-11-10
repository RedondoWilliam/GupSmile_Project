package gupsmile.com.ui.commonElements

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun TitleSectionTextPd(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    bottonAction: () -> Unit,
    @StringRes titleSectionText: Int
){

    Row (
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .height(20.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                bottonAction()
            }
    ){
        Spacer(modifier = modifier.width(15.dp))
        textCommonHomePage(
            stringResTextEntry = titleSectionText,
            maxLinesResParameter = 1,
            lineHeightParameter = 14.sp,
            fontSizeStyleParameter = 14.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_extra_light_italic)),
            colorStyleParameter = MaterialTheme.colorScheme.onBackground,
            modifier = modifier

        )
        Spacer(modifier = modifier.width(15.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun ForgotMyPasswordPreview(){
    GupsMileTheme {
        TitleSectionTextPd(
            bottonAction = {},
            titleSectionText = R.string.forgot_my_password
        )
    }
}
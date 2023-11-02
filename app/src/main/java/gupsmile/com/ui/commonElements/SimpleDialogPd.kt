package gupsmile.com.ui.commonElements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun SimpleDialogPd(
    modifier: Modifier = Modifier,
    @StringRes titleDialog: Int
){
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .padding(30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        textCommonHomePage(
            stringResTextEntry = titleDialog,
            maxLinesResParameter = 2,
            lineHeightParameter = 22.sp,
            fontSizeStyleParameter = 22.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
            colorStyleParameter = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Icon(
            painter = painterResource(id = R.drawable.hole_icon),
            contentDescription = "Search Icon",
            tint = Color.Unspecified,
            modifier = Modifier
                .size(200.dp)

        )
    }
}

@Composable
@Preview(showBackground = true)
fun SimpleDialogPreview(){
    GupsMileTheme {
        Column (
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            SimpleDialogPd(titleDialog = R.string.title_dialog_there_is_no_information_by_show)
        }
    }
}
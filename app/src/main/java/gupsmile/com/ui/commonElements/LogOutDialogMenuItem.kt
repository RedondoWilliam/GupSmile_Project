package gupsmile.com.ui.commonElements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun DropDownMenuItemPersonalized(
    modifier : Modifier = Modifier,
    @DrawableRes imageIcon: Int,
    @StringRes nameItemMenu: Int,
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(start = 8.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Icon(
            painter = painterResource(id = imageIcon),
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(17.dp)
        )
        Spacer(modifier = modifier.width(15.dp))
        textCommonHomePage(
            stringResTextEntry = nameItemMenu ,
            maxLinesResParameter = 1,
            lineHeightParameter = 15.sp,
            fontSizeStyleParameter = 15.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
            colorStyleParameter = Color.Black        )
    }
}

@Composable
@Preview(showBackground = true)
fun DropDownMenuItemPreview(){
    GupsMileTheme {
        DropDownMenuItemPersonalized(
            imageIcon = R.drawable.suggest_app,
            nameItemMenu =R.string.suggest_app
        )
    }
}
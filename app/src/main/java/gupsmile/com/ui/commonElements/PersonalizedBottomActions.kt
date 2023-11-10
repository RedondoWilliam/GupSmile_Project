package gupsmile.com.ui.commonElements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun PersonalizedBottomActions(
    modifier: Modifier = Modifier,
    actionsBottomText: Int,
    onConfirmActions: ()-> Unit,
    sizeFontTitleBottom: TextUnit = 15.sp,
    stileFontTitleBottom: FontFamily = FontFamily(Font(R.font.raleway_semi_bold))
){
    Row(
        modifier = modifier
            .clickable { onConfirmActions() }
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primary)
            .width(135.dp)
            .height(38.dp)
            .padding(start = 25.dp, end = 25.dp, top = 5.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        textCommonHomePage(
            stringResTextEntry = actionsBottomText,
            maxLinesResParameter = 1,
            lineHeightParameter = sizeFontTitleBottom,
            fontSizeStyleParameter = sizeFontTitleBottom,
            fontFamilyStyleParameter = stileFontTitleBottom,
            colorStyleParameter = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PersonalizedBottomActions(){
    GupsMileTheme {
        PersonalizedBottomActions(
           actionsBottomText = R.string.bottom_title_new_contact,
            onConfirmActions = {}
        )
    }
}
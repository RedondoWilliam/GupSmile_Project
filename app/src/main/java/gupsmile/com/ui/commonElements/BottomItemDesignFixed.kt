package gupsmile.com.ui.commonElements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun BottomItemDesignFixed(
    modifier: Modifier = Modifier,
    bottomActions: () -> Unit,
    padddingStart: Dp,
    paddingEnd: Dp,
    widthFixed: Dp,
    textBottonName: Int,
    sizeFontBottom: TextUnit = 15.sp

){
    Row(
        modifier = modifier
            .padding(start = padddingStart, end = paddingEnd)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.primary)
            .width(widthFixed)
            .height(37.dp)
            .clickable { bottomActions() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        textCommonHomePage(
            stringResTextEntry = textBottonName,
            maxLinesResParameter = 1,
            lineHeightParameter = sizeFontBottom,
            fontSizeStyleParameter = sizeFontBottom,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_medium)),
            colorStyleParameter = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BottomItemDesignFixedPreview(){
    GupsMileTheme {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                BottomItemDesignFixed(
                    bottomActions = {},
                    padddingStart = 5.dp,
                    paddingEnd = 5.dp,
                    widthFixed = 160.dp ,
                    textBottonName = R.string.login_bottom
                )
            }
        }
    }
}
package gupsmile.com.ui.commonElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun BarProgressPd(
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .fillMaxWidth()
            .height(25.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)

    ) {
        DialogLoading(
            sizeBox = 15.dp,
            sizeFigure = 12.dp
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .weight(0.9f)
        ) {
            textCommonHomePage(
                stringResTextEntry = R.string.title_bar_progress,
                maxLinesResParameter = 1,
                lineHeightParameter = 10.sp,
                fontSizeStyleParameter = 10.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                colorStyleParameter = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun BarProgressPdDarkPreview(){
    GupsMileTheme(
        darkTheme = true
    ) {
        BarProgressPd()
    }
}

@Composable
@Preview(showBackground = true)
fun BarProgressPdLightPreview(){
    GupsMileTheme {
        BarProgressPd()
    }
}
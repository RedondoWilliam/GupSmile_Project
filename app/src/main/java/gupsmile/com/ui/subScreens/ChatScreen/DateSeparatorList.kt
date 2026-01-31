package gupsmile.com.ui.subScreens.ChatScreen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun DateSeparator(
    modifier: Modifier = Modifier,
    date: String = "agosto 10, 2025"
){
    Surface(
        tonalElevation = 4.dp,
        shadowElevation = 1.dp,
        modifier = modifier
            //.padding(start = 4.dp, end = 4.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = 6.dp,
                    topStart = 6.dp,
                    bottomEnd = 6.dp,
                    bottomStart = 6.dp
                )
            )
        ,
        color =  MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f)

    ){
        Row(
            modifier = modifier
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            textCommonHomePageString(
                stringResTextEntry = date.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase() else it.toString()
                },
                maxLinesResParameter = 1,
                lineHeightParameter = 12.sp,
                fontSizeStyleParameter = 12.sp,
                fontFamilyStyleParameter =  FontFamily(Font(R.font.roboto_regular)) ,
                colorStyleParameter = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.9f),
                modifier = modifier
                    .padding(top = 4.dp, bottom = 4.dp, start = 5.dp, end = 5.dp)
                    .widthIn(max = 260.dp),
                letterSpacing = 0.sp
            )
        }
    }

}





@Preview(
    showBackground = true,
    name = "Date Separator",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    name = "Date Separator Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DateSeparatorPreview(){
    GupsMileTheme(
        dynamicColor = false,
    ) {
        Box(modifier =Modifier.padding(10.dp).wrapContentSize()) {

            DateSeparator()
        }


    }
}


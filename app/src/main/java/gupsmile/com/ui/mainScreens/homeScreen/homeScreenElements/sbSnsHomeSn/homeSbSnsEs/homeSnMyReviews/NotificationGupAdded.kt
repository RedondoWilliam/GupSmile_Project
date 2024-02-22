package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeSnMyReviews

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@SuppressLint("RememberReturnType")
@Composable
fun NotificationGupAdded(
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 5.dp,
                bottomStart = 0.dp,
                bottomEnd = 5.dp)
            )
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        textCommonHomePage(
            stringResTextEntry = R.string.notification_gup_added,
            maxLinesResParameter = 1,
            lineHeightParameter = 14.sp,
            fontSizeStyleParameter = 14.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
            colorStyleParameter = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }

}

@Composable
@Preview(showBackground = true)
fun NotificationGupAddedPreview(){
    GupsMileTheme {
        NotificationGupAdded()
    }
}
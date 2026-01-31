package gupsmile.com.ui.commonElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R

@Composable
fun notificationNetworkState(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .height(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.network_off_icon),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(14.dp)
            )
            Spacer(modifier = modifier.width(20.dp))
            textCommonHomePage(
                stringResTextEntry = R.string.notification_network_state_message,
                maxLinesResParameter = 5,
                lineHeightParameter = 12.sp,
                fontSizeStyleParameter = 12.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                colorStyleParameter = MaterialTheme.colorScheme.onBackground,
                textAlignPersonalized = TextAlign.Center
            )

        }
    }
}


@Preview
@Composable
fun notificationNetworkStatePreview(){
    notificationNetworkState()
}
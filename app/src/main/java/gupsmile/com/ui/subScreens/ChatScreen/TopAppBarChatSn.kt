package gupsmile.com.ui.subScreens.ChatScreen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun TopAppBarChatScreen(modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(start = 15.dp, end = 10.dp,),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {



        Row(
            modifier = modifier
                .width(230.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background)
                    .size(25.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id =  R.drawable.arrowback),
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(18.dp)
                )
            }


            Box(
                modifier = modifier
                    .padding(10.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .size(38.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.chat_person_icon),
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier
                        .size(22.dp)
                )
            }

            Column {
                textCommonHomePage(
                    stringResTextEntry = R.string.user_chat,
                    maxLinesResParameter = 1,
                    lineHeightParameter = 17.sp,
                    fontSizeStyleParameter = 17.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_semi_bold)) ,
                    colorStyleParameter = MaterialTheme.colorScheme.onBackground,
                    modifier = modifier

                )
                textCommonHomePage(
                    stringResTextEntry = R.string.state_chat,
                    maxLinesResParameter = 1,
                    lineHeightParameter = 10.sp,
                    fontSizeStyleParameter = 10.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_semi_bold)) ,
                    colorStyleParameter = MaterialTheme.colorScheme.onBackground,
                    modifier = modifier

                )
            }
        }

        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            Icon(
                painter = painterResource(id = R.drawable.volume_icon),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(25.dp)
                    .clickable {  }
            )

            Spacer(modifier = modifier.width(10.dp))

            Icon(
                painter = painterResource(id = R.drawable.call_icon),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(25.dp)
                    .clickable {  }
            )

            Spacer(modifier = modifier.width(10.dp))

            Icon(
                painter = painterResource(id = R.drawable.menu_icon),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(25.dp)
                    .clickable {  }
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
fun TopAppBarChatScreenPreview(){
    GupsMileTheme {
        TopAppBarChatScreen()
    }
}

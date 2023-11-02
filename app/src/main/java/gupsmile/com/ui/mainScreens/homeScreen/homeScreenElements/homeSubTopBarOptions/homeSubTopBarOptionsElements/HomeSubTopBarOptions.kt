package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeSubTopBarOptions.homeSubTopBarOptionsElements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun HomeSubTopBarOptionsElements(
    modifier: Modifier = Modifier,
    nameUser: String,
    messagesBottomActions: () -> Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 23.dp, start = 17.dp, top = 10.dp, bottom = 10.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row (
            modifier = modifier.width(200.dp)
        ){
            textCommonHomePageString(
                stringResTextEntry = nameUser,
                maxLinesResParameter = 1,
                lineHeightParameter = 19.sp,
                fontSizeStyleParameter = 19.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_semi_bold)),
                colorStyleParameter = MaterialTheme.colorScheme.onBackground,

                )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .width(150.dp)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.search_two),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(25.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .clip(CircleShape)
                    .size(29.dp)
                    .background(Color.White)
                    .clickable { messagesBottomActions() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.message),
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(27.dp)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = modifier
                    .size(27.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.like),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = modifier
                    .size(27.dp)
            )
        }


    }
}

@Composable
@Preview(showBackground = true)
fun HomeSubTopBarOptionsElementsPreview(){
    GupsMileTheme {
        HomeSubTopBarOptionsElements(
            nameUser = "William",
            messagesBottomActions = {}
        )
    }
}
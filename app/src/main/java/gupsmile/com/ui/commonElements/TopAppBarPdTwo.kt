package gupsmile.com.ui.commonElements

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun TopAppBarPdTwo(
    modifier: Modifier = Modifier,
    navigationHomeProfile: () -> Unit,
    profilePhoto: @Composable () -> Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 10.dp,),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        textCommonHomePage(
            stringResTextEntry = R.string.app_name_two,
            maxLinesResParameter = 1,
            lineHeightParameter = 27.sp,
            fontSizeStyleParameter = 27.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_bold)) ,
            colorStyleParameter = MaterialTheme.colorScheme.onBackground,
            modifier = modifier

        )
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            Box(
                modifier = modifier
                    .padding(10.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .size(38.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(22.dp)
                )
            }

            Box(
                modifier = modifier
                    .padding(10.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .size(38.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search_two),
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(17.dp)
                )
            }

            Spacer(modifier = modifier.width(10.dp))
            Box(
                modifier = modifier
                    .padding(end = 10.dp)
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { navigationHomeProfile() },
                contentAlignment = Alignment.Center
            ) {
                profilePhoto()
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun TopAppBarPdTwoPreview(){
    GupsMileTheme {
        TopAppBarPdTwo(
            navigationHomeProfile = {},
            profilePhoto = {}
        )
    }
}
package gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.nameToolsEditProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
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
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R

@Composable
fun NameToosEditProfileScreen(
    modifier: Modifier = Modifier,
    nameUser: String
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 15.dp, bottom = 15.dp, end = 12.dp)
    ) {
        Row(
            modifier = modifier.width(250.dp)
        ) {
            textCommonHomePageString(
                stringResTextEntry = nameUser,
                maxLinesResParameter = 1,
                lineHeightParameter = 20.sp,
                fontSizeStyleParameter = 20.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_semi_bold)),
                colorStyleParameter = Color.Black
            )
        }
        Row {
            Icon(
                painter = painterResource(id = R.drawable.setting),
                contentDescription = "Search Icon",
                tint = Color.Black,
                modifier = Modifier
                    .size(22.dp)
            )
            Spacer(modifier = modifier.width(16.dp))
            Icon(
                painter = painterResource(id = R.drawable.edit),
                contentDescription = "Search Icon",
                tint = Color.Black,
                modifier = Modifier
                    .size(22.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NameToolsEditProfileScreenPreview(){
    NameToosEditProfileScreen(nameUser = "William Redondo")
}

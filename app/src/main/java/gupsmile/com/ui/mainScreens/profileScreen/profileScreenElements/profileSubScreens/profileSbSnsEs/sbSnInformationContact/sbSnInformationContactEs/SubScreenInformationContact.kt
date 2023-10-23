package gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.profileSbSnsEs.sbSnInformationContact.sbSnInformationContactEs

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.ensayo.example.ui.commonElements.textCommonHomePage
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun SubScreenInformationContact(
    modifier: Modifier = Modifier,
    phoneUser: String,
    emailUser: String,
    adressUser: String
){
    Column(
        modifier = modifier
            .padding(top = 15.dp, bottom = 15.dp)
    ) {
        InformationContactItem(
            nameItem = R.string.phone,
            phoneUser = phoneUser,
            iconItemContact = R.drawable.phone
        )
        Spacer(modifier = modifier.height(30.dp))
        InformationContactItem(
            nameItem = R.string.email,
            phoneUser = emailUser,
            iconItemContact =R.drawable.emailicon
        )
        Spacer(modifier = modifier.height(30.dp))
        InformationContactItem(
            nameItem = R.string.adress,
            phoneUser = adressUser,
            iconItemContact = R.drawable.adress_icon
        )
    }
}


@Composable
fun InformationContactItem(
    modifier: Modifier = Modifier,
    @StringRes nameItem: Int,
    phoneUser: String,
    @DrawableRes iconItemContact: Int


){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            textCommonHomePage(
                stringResTextEntry = nameItem,
                maxLinesResParameter = 1,
                lineHeightParameter = 13.sp,
                fontSizeStyleParameter = 13.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                colorStyleParameter = Color.Black,
                modifier = modifier.padding(bottom = 5.dp)
            )
            textCommonHomePageString(
                stringResTextEntry = phoneUser,
                maxLinesResParameter = 1 ,
                lineHeightParameter = 15.sp ,
                fontSizeStyleParameter = 15.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                colorStyleParameter =Color.Black
            )
        }
        Icon(
            painter = painterResource(id = iconItemContact),
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(20.dp)
        )

    }
}
@Composable
@Preview(showBackground = true)
fun SubScreenInformationContactPreview(){
    GupsMileTheme {
        SubScreenInformationContact(
            phoneUser = "3142908556",
            emailUser = "william.redondo010@gmail.com" ,
            adressUser = "Soracá, Boyacá, Colombia"
        )
    }
}
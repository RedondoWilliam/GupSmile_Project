package gupsmile.com.ui.subScreens.infoLocalContactSn.infoLocalContactEs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R


@Composable
fun InformationContactScn(
    modifier: Modifier = Modifier,
    informationContactItems: @Composable () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start= 18.dp, end = 10.dp, top = 15.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        textCommonHomePage(
            stringResTextEntry = R.string.description_section_profile_screen,
            maxLinesResParameter = 2,
            lineHeightParameter = 20.sp,
            fontSizeStyleParameter = 20.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
            colorStyleParameter = MaterialTheme.colorScheme.onBackground
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(end = 10.dp, top = 10.dp, bottom = 20.dp)

        ) {
            informationContactItems()
        }
    }
}

@Composable
fun InformationContactItemInfo(
    modifier: Modifier = Modifier,
    iconItem: Int,
    contentIntoText: String,
    describeInfoText: String,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        if(contentIntoText != ""){
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .size(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconItem),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = modifier.size(17.dp)
                )
            }

            if(describeInfoText != ""){
                Column(
                    modifier = modifier
                        .padding(start = 20.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {

                    Row {
                        textCommonHomePageString(
                            stringResTextEntry = contentIntoText,
                            maxLinesResParameter = 1,
                            lineHeightParameter = 16.sp ,
                            fontSizeStyleParameter = 16.sp,
                            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                            colorStyleParameter = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Spacer(modifier = modifier.height(3.dp))
                    Row {
                        textCommonHomePageString(
                            stringResTextEntry = describeInfoText,
                            maxLinesResParameter = 1,
                            lineHeightParameter = 15.sp ,
                            fontSizeStyleParameter = 15.sp,
                            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                            colorStyleParameter = MaterialTheme.colorScheme.onBackground
                        )
                    }

                }
            }else{
                Row(
                    modifier = modifier
                        .padding(start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    textCommonHomePageString(
                        stringResTextEntry = contentIntoText,
                        maxLinesResParameter = 1,
                        lineHeightParameter = 16.sp ,
                        fontSizeStyleParameter = 16.sp,
                        fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                        colorStyleParameter = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun InformationContactScnPreview(
    modifier: Modifier = Modifier,
    ) {
    InformationContactScn(
        informationContactItems = {
            InformationContactItemInfo(
                iconItem = R.drawable.phone_icon_info_contact,
                contentIntoText = "+57 3142908556",
                describeInfoText = "Teléfono móvil",
            )
            InformationContactItemInfo(
                iconItem = R.drawable.email_icon_info_contact,
                contentIntoText = "lucía-hernandez@gmail.com",
                describeInfoText = "",
            )
            InformationContactItemInfo(
                iconItem = R.drawable.addres_icon_add_contact,
                contentIntoText = "Calle 2 No. 3-56, Soracá -Boyacá",
                describeInfoText = "",
            )
        }
    )
}

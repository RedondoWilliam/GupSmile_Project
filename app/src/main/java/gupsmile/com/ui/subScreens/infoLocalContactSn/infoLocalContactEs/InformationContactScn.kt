package gupsmile.com.ui.subScreens.infoLocalContactSn.infoLocalContactEs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R


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
            .padding(start = 20.dp, top = 15.dp, end = 20.dp, bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        if(contentIntoText != ""){
            Icon(
                painter = painterResource(id = iconItem),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = modifier.size(18.dp)
            )

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
                            lineHeightParameter = 18.sp ,
                            fontSizeStyleParameter = 18.sp,
                            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                            colorStyleParameter = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                    Spacer(modifier = modifier.height(5.dp))
                    Row {
                        textCommonHomePageString(
                            stringResTextEntry = describeInfoText,
                            maxLinesResParameter = 1,
                            lineHeightParameter = 18.sp ,
                            fontSizeStyleParameter = 18.sp,
                            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                            colorStyleParameter = MaterialTheme.colorScheme.onTertiaryContainer
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
                        lineHeightParameter = 18.sp ,
                        fontSizeStyleParameter = 18.sp,
                        fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                        colorStyleParameter = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun InformationContactItemInfoPreview(
    modifier: Modifier = Modifier,

    ){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 10.dp, top = 10.dp, bottom = 20.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 15.dp,
                        bottomEnd = 15.dp,
                        bottomStart = 15.dp
                    )
                )
                .background(MaterialTheme.colorScheme.primaryContainer)

        ) {
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
    }
}

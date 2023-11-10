package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeCardContentItem.homeCardContentElements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import com.ensayo.example.ui.topAppBarHomePage.topBarElements.ProfileImageHome
import gupsmile.com.R
import gupsmile.com.ui.commonElements.DropDownMenuItemPersonalized
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun HomeCardContentItem(
    modifier: Modifier = Modifier,
    nameUser: String,
    timePost: String,
    reactionsLikes: String,
    reactionsComments: String,
    addContentMedia: @Composable () -> Unit,
    descriptionPost: @Composable () -> Unit,
    actionsMenuBottom: () -> Unit = {}
){
   Box{
       Column(
           modifier = modifier
               .fillMaxWidth()
               .background(MaterialTheme.colorScheme.background)
       ) {
           Row(
               modifier = modifier
                   .fillMaxWidth()
                   .padding(start = 10.dp, end = 15.dp, top = 15.dp, bottom = 5.dp)
                   .background(MaterialTheme.colorScheme.background),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment =  Alignment.CenterVertically
           ) {
               Row(
                   horizontalArrangement = Arrangement.Center,
                   verticalAlignment =  Alignment.CenterVertically
               ) {
                   ProfileImageHome(
                       navigationHomeProfile = {},
                       profileImage = painterResource(id = R.drawable.selfie)
                   )
                   Spacer(modifier = modifier.width(10.dp))
                   textCommonHomePageString(
                       stringResTextEntry = nameUser,
                       maxLinesResParameter = 1,
                       lineHeightParameter = 14.sp,
                       fontSizeStyleParameter =14.sp ,
                       fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                       colorStyleParameter = MaterialTheme.colorScheme.onBackground
                   )
               }
               Row(
                   horizontalArrangement = Arrangement.End,
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   Icon(
                       painter = painterResource(id = R.drawable.share_two),
                       contentDescription = "Search Icon",
                       tint = MaterialTheme.colorScheme.onBackground,
                       modifier = Modifier
                           .size(22.5.dp)
                   )
                   Spacer(modifier = modifier.width(22.dp))
                   Icon(
                       painter = painterResource(id = R.drawable.menu_down_icon),
                       contentDescription = "Search Icon",
                       tint = MaterialTheme.colorScheme.onBackground,
                       modifier = Modifier
                           .size(25.dp)
                           .clickable {actionsMenuBottom()}
                   )
               }
           }
           addContentMedia()
           CardContent(
               nameUser = nameUser,
               descriptionPost = descriptionPost,
               timePost = timePost,
               reactionsLikes = reactionsLikes,
               reactionsComments =reactionsComments
           )
       }

   }
}

@Composable
fun CardContent(
    modifier: Modifier = Modifier,
    nameUser: String,
    timePost: String,
    reactionsLikes: String,
    reactionsComments: String,
    descriptionPost: @Composable () -> Unit

    ) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 20.dp, top = 10.dp, bottom = 15.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        descriptionPost()
        Spacer(modifier = modifier.height(5.dp))
        textCommonHomePageString(
            stringResTextEntry = timePost,
            maxLinesResParameter = 1 ,
            lineHeightParameter = 12.sp,
            fontSizeStyleParameter =12.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
            colorStyleParameter = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = modifier.height(4.dp))
        textCommonHomePageString(
            stringResTextEntry = "$reactionsLikes likes, $reactionsComments comentarios" ,
            maxLinesResParameter = 1,
            lineHeightParameter = 14.sp ,
            fontSizeStyleParameter = 14.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
            colorStyleParameter = MaterialTheme.colorScheme.onBackground,
            modifier =  modifier.padding(bottom = 3.dp)
        )
        Spacer(modifier = modifier.height(6.dp))
        CardContentIcons()
    }
}


@Composable
fun CardContentIcons(
    modifier: Modifier = Modifier,
){
    Row(
        modifier =modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment =  Alignment.CenterVertically
    ) {
       Row (
           modifier =modifier,
           horizontalArrangement = Arrangement.Center,
           verticalAlignment =  Alignment.CenterVertically
       ){
           Icon(
               painter = painterResource(id = R.drawable.like),
               contentDescription = "Search Icon",
               tint = MaterialTheme.colorScheme.onBackground,
               modifier = Modifier
                   .size(26.dp)
           )
           Spacer(modifier = modifier.width(27.dp))
           Icon(
               painter = painterResource(id = R.drawable.comment_three),
               contentDescription = "Search Icon",
               tint = MaterialTheme.colorScheme.onBackground,
               modifier = Modifier
                   .size(25.dp)
           )
       }
       Row(
           modifier =modifier,
           horizontalArrangement = Arrangement.Center,
           verticalAlignment =  Alignment.CenterVertically
       ) {
           Spacer(modifier = modifier.width(26.dp))
           Icon(
               painter = painterResource(id = R.drawable.save_two),
               contentDescription = "Search Icon",
               tint = MaterialTheme.colorScheme.onBackground,
               modifier = Modifier
                   .size(25.dp)
           )
       }
    }
}


@Composable
fun CardContentIconsReactions(
    modifier: Modifier = Modifier,
    countReactions: String,
    @DrawableRes iconReaction: Int
){
    Row(
        modifier =modifier,
        verticalAlignment =  Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ){
        textCommonHomePageString(
            stringResTextEntry = countReactions,
            maxLinesResParameter = 1,
            lineHeightParameter = 16.sp ,
            fontSizeStyleParameter = 16.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
            colorStyleParameter = Color.Black,
            modifier =  modifier.padding(bottom = 3.dp)
        )
        Spacer(modifier = modifier.width(5.dp))
        Icon(
            painter = painterResource(id = iconReaction),
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(21.dp)
        )

    }
}

@Composable
@Preview(showBackground = true)
fun HomeCardContentItemPreview(){
    GupsMileTheme {
        HomeCardContentItem(
            nameUser = "Lucia Hernández",
//            descriptionPost = "Son unos pendejos, pero siguen siendo mis empleados. Saludos desde " +
//                    "la oficina.",
            timePost = "hace 30 minutos" ,
            reactionsLikes = "1.2k",
            reactionsComments = "145",
            addContentMedia = {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.selfie),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth

                )
            },
            descriptionPost = {
                    textCommonHomePageString(
                        stringResTextEntry = "Son unos pendejos, pero siguen siendo mis empleados. Saludos desde la oficina" ,
                        maxLinesResParameter = 100 ,
                        lineHeightParameter = 14.sp ,
                        fontSizeStyleParameter = 14.sp,
                        fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                        colorStyleParameter = MaterialTheme.colorScheme.onBackground
                    )
            }
        )
    }
}
@Composable
@Preview(showBackground = true)
fun HomeCardContentItemPreviewTwo(){
    GupsMileTheme {
        HomeCardContentItem(
            nameUser = "Lucia Hernández",
            timePost = "hace 30 minutos" ,
            reactionsLikes = "1.2k",
            reactionsComments = "145",
            addContentMedia = {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id =R.drawable.selfie_dos),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth

                )
            },
            descriptionPost = {
                textCommonHomePageString(
                    stringResTextEntry = "Son unos pendejos, pero siguen siendo mis empleados. Saludos desde la oficina" ,
                    maxLinesResParameter = 100 ,
                    lineHeightParameter = 14.sp ,
                    fontSizeStyleParameter = 14.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                    colorStyleParameter = MaterialTheme.colorScheme.onBackground
                )
            }
        )
    }


}

@Composable
@Preview(showBackground = true)
fun HomeCardContentItemDarkModePreview(){
    GupsMileTheme(
        darkTheme = true
    ) {
        HomeCardContentItem(
            nameUser = "Lucia Hernández",
            timePost = "hace 30 minutos" ,
            reactionsLikes = "1.2k",
            reactionsComments = "145",
            addContentMedia = {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.selfie),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth

                )
            },
            descriptionPost = {
                textCommonHomePageString(
                    stringResTextEntry = "Son unos pendejos, pero siguen siendo mis empleados. Saludos desde la oficina" ,
                    maxLinesResParameter = 100 ,
                    lineHeightParameter = 14.sp ,
                    fontSizeStyleParameter = 14.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                    colorStyleParameter = MaterialTheme.colorScheme.onBackground
                )
            }
        )
    }
}
@Composable
@Preview(showBackground = true)
fun HomeCardContentItemDarkModePreviewTwo(){
    GupsMileTheme(
        darkTheme = true
    ) {
        HomeCardContentItem(
            nameUser = "Lucia Hernández",
            timePost = "hace 30 minutos" ,
            reactionsLikes = "1.2k",
            reactionsComments = "145",
            addContentMedia = {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id =R.drawable.selfie_dos),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth

                )
            },
            descriptionPost = {
                textCommonHomePageString(
                    stringResTextEntry = "Son unos pendejos, pero siguen siendo mis empleados. Saludos desde la oficina" ,
                    maxLinesResParameter = 100 ,
                    lineHeightParameter = 14.sp ,
                    fontSizeStyleParameter = 14.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                    colorStyleParameter = MaterialTheme.colorScheme.onBackground
                )
            }
        )
    }


}
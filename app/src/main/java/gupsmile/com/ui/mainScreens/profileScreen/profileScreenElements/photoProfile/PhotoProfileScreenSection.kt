package gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.photoProfile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gupsmile.com.R
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun PhotoProfileScreenSection(
    modifier: Modifier  = Modifier,
     profileImage: Painter
){


    Box(
        modifier = modifier
            .height(402.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            painter = profileImage,
            contentDescription = "",
            modifier = modifier.fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

       Row(
           modifier = modifier
               .padding(end = 10.dp, bottom = 10.dp),
           horizontalArrangement = Arrangement.End
       ){
           FloatingBottonDesignFixed(
               imageIcon = R.drawable.camera,
               sizeIcon = 21.dp,
           )
       }

    }
}

@Composable
@Preview(showBackground = true)
fun ProfilePerfilPhotoPreview(){
    GupsMileTheme {
        PhotoProfileScreenSection(
            profileImage =  painterResource(id = R.drawable.profile_image)
        )
    }
}
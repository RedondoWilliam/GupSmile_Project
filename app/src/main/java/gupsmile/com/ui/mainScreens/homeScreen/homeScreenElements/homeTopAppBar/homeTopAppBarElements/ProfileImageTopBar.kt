package com.ensayo.example.ui.topAppBarHomePage.topBarElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun ProfileImageHome(
    modifier: Modifier = Modifier,
    sizeImage: Dp = 30.dp,
    navigationHomeProfile: () -> Unit = {},
    profileImage: Painter
) {
    Image(
        modifier = modifier
            .size(sizeImage)
            .clip(CircleShape)
            .clickable { navigationHomeProfile() },
        painter = profileImage,
        contentDescription = "description",
        contentScale = ContentScale.Crop,

    )
}

@Composable
@Preview
fun ProfileImagePreview(){
    GupsMileTheme{
        Surface {
            ProfileImageHome(
                navigationHomeProfile = {},
                profileImage = painterResource(id = R.drawable.profile_image)
            )
        }
    }
}

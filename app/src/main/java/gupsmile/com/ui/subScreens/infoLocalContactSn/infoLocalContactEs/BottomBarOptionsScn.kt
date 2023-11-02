package gupsmile.com.ui.subScreens.infoLocalContactSn.infoLocalContactEs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun BottomBarOptionsScn(
    modifier: Modifier = Modifier,
    emailBottomBarActionBottom: () -> Unit,
    chatBottomBarActionBottom: () -> Unit,
    phoneBottomBarActionBottom:() -> Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BottomBarOptionsItem(
            iconItem = R.drawable.email_icon_info_contact,
            bottomActionsItem = {emailBottomBarActionBottom()}
        )
        BottomBarOptionsItem(
            iconItem = R.drawable.chat_icon_info_contact,
            bottomActionsItem = {chatBottomBarActionBottom()}
        )
        BottomBarOptionsItem(
            iconItem = R.drawable.phone_icon_info_contact,
            bottomActionsItem = {phoneBottomBarActionBottom()}
        )
    }
}

@Composable
fun BottomBarOptionsItem(
    modifier: Modifier = Modifier,
    iconItem: Int,
    bottomActionsItem: () -> Unit
){
    Box(
        modifier = modifier
            .clickable { bottomActionsItem() }
            .size(55.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconItem),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = modifier.size(24.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BottomBarOptionsScnPreview(){
    GupsMileTheme {
        BottomBarOptionsScn(
            emailBottomBarActionBottom = {},
            chatBottomBarActionBottom = {},
            phoneBottomBarActionBottom = {}
        )
    }
}
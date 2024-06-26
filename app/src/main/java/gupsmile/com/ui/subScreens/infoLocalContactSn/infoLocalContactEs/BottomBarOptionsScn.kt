package gupsmile.com.ui.subScreens.infoLocalContactSn.infoLocalContactEs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gupsmile.com.R
import gupsmile.com.ui.commonElements.CircleShapeBottomPd
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
        CircleShapeBottomPd(
            iconItem = R.drawable.email_icon_info_contact,
            bottomActionsItem = {emailBottomBarActionBottom()}
        )
        CircleShapeBottomPd(
            iconItem = R.drawable.chat_icon_info_contact,
            bottomActionsItem = {chatBottomBarActionBottom()}
        )
        CircleShapeBottomPd(
            iconItem = R.drawable.phone_icon_info_contact,
            bottomActionsItem = {phoneBottomBarActionBottom()}
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
package gupsmile.com.ui.commonElements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun CircleShapeBottomPd(
    modifier: Modifier = Modifier,
    bottomActionsItem: () -> Unit,
    @DrawableRes iconItem: Int,
    containerColorIcon: Color = MaterialTheme.colorScheme.primaryContainer,
    colorIcon: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    sizeIcon: Dp = 24.dp,
    sizecontainerIcon: Dp = 55.dp
){
    Box(
        modifier = modifier
            .clickable { bottomActionsItem() }
            .size(sizecontainerIcon)
            .clip(CircleShape)
            .background(containerColorIcon),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconItem),
            contentDescription = "",
            tint =colorIcon,
            modifier = modifier.size(sizeIcon)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CircleShapeBottomPdPreview(){
    GupsMileTheme {
        CircleShapeBottomPd(
            bottomActionsItem = { /*TODO*/ },
            iconItem = R.drawable.phone_icon_info_contact
        )
    }
}
package gupsmile.com.ui.subScreens.ChatScreen

import android.content.res.Configuration
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.decode.ImageSource
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.ui.theme.GupsMileTheme
import gupsmile.com.R



data class SelectMessageBarActions(
    val numberItemsSelected: Int = 0,
    val arrowbackActions: () -> Unit = {},
    val editMessageAction: () -> Unit = {},
    val trashMessageAction: () -> Unit = {},
    val copiesMessageAction: () -> Unit = {},
    val replyMessageAction: () -> Unit = {},
    val shareMessageAction: () -> Unit = {}
)


@Composable
fun SelectMessageBar(
    modifier: Modifier = Modifier,
    selectMessageBarActions: SelectMessageBarActions
){

    val withSpacer: Dp = 13.dp

    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconLayoutSelectMessageBar(
                icon = painterResource(R.drawable.arrowback),
                clickActionsIcon = { selectMessageBarActions.arrowbackActions() },
                sizeIcon = 22.dp
            )

            Spacer(modifier = Modifier.width(withSpacer))

            textCommonHomePageString(
                stringResTextEntry = selectMessageBarActions.numberItemsSelected.toString(),
                colorStyleParameter = MaterialTheme.colorScheme.onBackground,
                maxLinesResParameter = 1,
                lineHeightParameter = 25.sp,
                fontSizeStyleParameter = 25.sp,
                fontFamilyStyleParameter = MaterialTheme.typography.bodyMedium.fontFamily
            )

        }

        Row(
            modifier = Modifier.wrapContentSize()
        ) {

            IconLayoutSelectMessageBar(
                icon = painterResource(R.drawable.editmessage_icon_svg),
                clickActionsIcon = { selectMessageBarActions.editMessageAction() },
                sizeIcon = 25.dp
            )

            Spacer(modifier = Modifier.width(withSpacer))

            IconLayoutSelectMessageBar(
                icon = painterResource(R.drawable.trash_icon),
                clickActionsIcon = { selectMessageBarActions.trashMessageAction() },
                sizeIcon = 25.dp
            )

            Spacer(modifier = Modifier.width(withSpacer))

            IconLayoutSelectMessageBar(
                icon = painterResource(R.drawable.copies_icon),
                clickActionsIcon = { selectMessageBarActions.copiesMessageAction() },
                sizeIcon = 23.dp
            )

            Spacer(modifier = Modifier.width(withSpacer))

            IconLayoutSelectMessageBar(
                icon = painterResource(R.drawable.reply_icon),
                clickActionsIcon = { selectMessageBarActions.replyMessageAction() },
                sizeIcon = 25.dp
            )

            Spacer(modifier = Modifier.width(withSpacer))

            IconLayoutSelectMessageBar(
                icon = painterResource(R.drawable.sharemessage_icon),
                clickActionsIcon = { selectMessageBarActions.shareMessageAction() },
                sizeIcon = 25.dp
            )

            Spacer(modifier = Modifier.width(withSpacer))

        }




    }
}


@Composable
fun IconLayoutSelectMessageBar(
    modifier: Modifier = Modifier,
    icon: Painter,
    clickActionsIcon: () -> Unit = {},
    colorIcon: Color = MaterialTheme.colorScheme.onBackground,
    sizeIcon: Dp = 17.dp,
    sizeContainerIcon: Dp = 37.dp

){
    Box(
        modifier = modifier
            .wrapContentSize()
            .size(sizeContainerIcon)
            .clip(CircleShape)
            .clickable{ clickActionsIcon() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            tint = colorIcon,
            modifier = Modifier
                .size(size = sizeIcon)

        )
    }

}


@Preview(
    showBackground = true,
    name = "Date Separator",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    name = "Date Separator Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SelectMessageBarPreview(){
    GupsMileTheme {
        SelectMessageBar(selectMessageBarActions = SelectMessageBarActions())
    }
}

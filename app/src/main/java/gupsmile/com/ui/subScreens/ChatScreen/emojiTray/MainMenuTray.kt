package gupsmile.com.ui.subScreens.ChatScreen.emojiTray

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import gupsmile.com.R
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.SectionsHorizontalPager
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun MainMenuTray(
    modifier: Modifier = Modifier,
    contentTabRow: @Composable () -> Unit = {},
    searchAction: () -> Unit = {}
){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier.width(6.dp))
        Icon(
            painter = painterResource(id =R.drawable.search_two),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    onClick = searchAction
                ),
            tint = MaterialTheme.colorScheme.onBackground
        )

        Row(
            modifier = modifier
                .fillMaxWidth(0.5f)
                .clip(RoundedCornerShape(50.dp))
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(start = 20.dp, top = 5.dp, bottom = 5.dp, end = 20.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            contentTabRow()
        }

        Icon(
            painter = painterResource(id =R.drawable.delete_border),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    onClick = { /*TODO*/ }
                ),
            tint = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier.width(6.dp))
    }
    

}



@Composable
fun EmojiTrayTab(
    section: SectionsHorizontalPager,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){

    val colorIcon by animateColorAsState(
        if(selected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        label = "tabIcon"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = section.icon),
            contentDescription = section.contentDescription,
            tint = colorIcon,
            modifier = Modifier
                .size(22.dp)
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
fun MainMenuTrayPreview(){
    GupsMileTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ){
            MainMenuTray(
                contentTabRow = {
                    SectionsHorizontalPager.entries.forEachIndexed { index, section ->
                        EmojiTrayTab(
                            section = section,
                            selected = section == SectionsHorizontalPager.StickerTray,
                            onClick = {  }
                        )
                    }
                }
            )
        }
    }
}
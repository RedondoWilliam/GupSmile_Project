package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun StickerTray(
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "StickerTray"
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
fun StickerTrayPreview(){
    GupsMileTheme {
        StickerTray()
    }
}

package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import gupsmile.com.R


@Composable
fun proofEmojis(modifier: Modifier = Modifier){

    val ime = WindowInsets.ime
    val imeHeight = ime.getBottom(LocalDensity.current)
    var keyboardHeight by remember { mutableIntStateOf(0) }
    var text by remember { mutableStateOf("") }
    val density = LocalDensity.current


    Column(
        modifier = Modifier.fillMaxSize().padding(25.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top
    ) {
        LaunchedEffect(true) {
            snapshotFlow { ime.getBottom(density) }
                .collect { newImeHeight ->
                    keyboardHeight = newImeHeight
                }
        }

        EmojiItem()

        Icon(
            painter = painterResource(id = R.drawable.emojiii),
            contentDescription = null,
            modifier = Modifier.size(90.dp),
            tint = Color.Unspecified
        )
        Text(
            text = keyboardHeight.toString()

        )
        TextField(
            value = text + keyboardHeight.toString() + imeHeight,
            onValueChange = { text = it },
        )
    }

}




val emojiList = listOf(
    "file:///android_asset/emojis/emoji_u1f611.svg",
    "file:///android_asset/emojis/emoji_u1f611.svg",
    "file:///android_asset/emojis/emoji_u1f611.svg",
    "file:///android_asset/emojis/emoji_u1f611.svg"

)





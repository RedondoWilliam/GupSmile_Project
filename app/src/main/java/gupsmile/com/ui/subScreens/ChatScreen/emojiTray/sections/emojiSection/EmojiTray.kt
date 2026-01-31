package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection

import android.R.attr.title
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowInsets
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.ui.subScreens.ChatScreen.ChatScreenMode
import gupsmile.com.ui.subScreens.ChatScreen.EmojiSearchMode.Active
import gupsmile.com.ui.subScreens.ChatScreen.UiStateScreen
import gupsmile.com.ui.subScreens.ChatScreen.data.Message
import gupsmile.com.ui.subScreens.ChatScreen.data.toFormattedDateTime
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.EmojiTrayHeightDp
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.TextFieldTrayBar
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji.Emoji
import gupsmile.com.ui.theme.GupsMileTheme
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EmojiTray(
    modifier: Modifier = Modifier,
    emojiTrayUiState: EmojiTrayHeightDp,
    emojiList: List<EmojiGridItem> = emptyList(),
    emojiSearchResults: List<EmojiDetails> = emptyList(),
    screenUiState: UiStateScreen = UiStateScreen()
){

    val screenConfiguration = LocalConfiguration.current
    val height =
        if(screenConfiguration.orientation ==Configuration.ORIENTATION_PORTRAIT){
        emojiTrayUiState.heightEmojiTrayPortrait
        }else{
            emojiTrayUiState.heightEmojiTrayLandscape
        }


    Column(
        modifier = modifier
            .height( height*1.8f)
            .padding(top = 3.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        when(val mode = screenUiState.mode){
            is ChatScreenMode.EmojiTray -> {
                if(mode.searchMode is Active){
                    GridEmojisSearchResults(
                        emojiSearchResults = emojiSearchResults
                    )
                }else{
                    GridEmojis(
                        emojiList = emojiList,
                    )
                }
            }else -> Unit
        }

    }

}



@Composable
fun GridEmojisSearchResults(
    modifier: Modifier = Modifier,
    emojiSearchResults: List<EmojiDetails> = emptyList()
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start =3.dp, end = 3.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 45.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = emojiSearchResults,
                key = { item -> item.id }
            ) { item ->
                EmojiItem(emojiPath = item.path)
            }
        }
    }

}




@Composable
fun GridEmojis(
    modifier: Modifier = Modifier,
    emojiList: List<EmojiGridItem> = emptyList(),
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start =3.dp, end = 3.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 45.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items= emojiList,
                key  = { item ->
                    when (item) {
                        is EmojiGridItem.Header -> "header_${item.category}"
                        is EmojiGridItem.Emoji -> item.data.id
                    }
                },
                span = { item ->
                    when (item) {
                        is EmojiGridItem.Header -> GridItemSpan(maxLineSpan)
                        is EmojiGridItem.Emoji -> GridItemSpan(1)
                    }
                }
            ){item ->
                when(item){
                    is EmojiGridItem.Header -> {
                        CategoryHeader(title = item.category)
                    }
                    is EmojiGridItem.Emoji -> {
                        EmojiItem(emojiPath = item.data.path)
                    }
                }
            }


        }

    }
}



@Composable
fun CategoryHeader(
    modifier: Modifier = Modifier,
    title: String
){
    textCommonHomePageString(
        stringResTextEntry = title,
        maxLinesResParameter = 1,
        lineHeightParameter = 14.sp,
        fontSizeStyleParameter = 14.sp,
        fontFamilyStyleParameter =  FontFamily(Font(R.font.roboto_semibold)) ,
        colorStyleParameter = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .padding(vertical = 15.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        letterSpacing = 0.sp
    )

}


@Composable
fun EmojiItem(modifier: Modifier = Modifier,
              emojiPath: String = "file:///android_asset/emojis/emoji_u1f611.webp"
) {
    Box(
        modifier = modifier
            .size(40.dp),
        contentAlignment = Alignment.Center

    ) {
        AsyncImage(
            model =  ImageRequest.Builder(LocalContext.current)
                .data(emojiPath)
                .listener(
                    onError = {_, result ->
                        Log.e("EmojiItem", "Error loading emoji", result.throwable)
                    }
                )
                .build(),
            contentDescription = "null",
            modifier = Modifier.size(34.dp)

        )
    }

}



@Composable
fun rememberKeyboardhight(): Int{
    val view = LocalView.current
    var keyboardHeight by remember{mutableStateOf(0)}


    DisposableEffect(view) {
        val listener = View.OnApplyWindowInsetsListener{_, insets ->
            val imeHeight = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                insets.getInsets(WindowInsets.Type.ime()).bottom
            }else{
                @Suppress("DEPRECATION")
                insets.systemWindowInsetBottom
            }

            keyboardHeight = imeHeight
            insets
        }
        view.setOnApplyWindowInsetsListener(listener)
        onDispose { view.setOnApplyWindowInsetsListener(null) }
    }

    return keyboardHeight

}



@Composable
fun lazyPagingItemsEmojisPreview(): LazyPagingItems<Emoji> {
    val fakeMessages = listOf(
       Emoji(
           id = 1,
           path = "file:///android_asset/emojis/emoji_u1f611.webp",
           isRecent = false,
           category = "",
           unicodeName = "",
           keywords = ""
       ),
        Emoji(
            id = 1,
            path = "file:///android_asset/emojis/emoji_u1f611.webp",
            isRecent = false,
            category = "",
            unicodeName = "",
            keywords = ""
        ),
        Emoji(
            id = 1,
            path = "file:///android_asset/emojis/emoji_u1f611.webp",
            isRecent = false,
            category = "",
            unicodeName = "",
            keywords = ""
        )
    )

    return  flowOf(PagingData.from(fakeMessages)).collectAsLazyPagingItems()
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
fun EmojiTrayPreview(){
    GupsMileTheme {
        EmojiTray(
            emojiTrayUiState = EmojiTrayHeightDp(
                heightEmojiTrayLandscape = 302.dp,
                heightEmojiTrayPortrait = 200.dp
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenWithEmojiTrayPreview(){
    GupsMileTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom){
            EmojiTray(
                emojiTrayUiState = EmojiTrayHeightDp(
                    heightEmojiTrayLandscape = 302.dp,
                    heightEmojiTrayPortrait = 200.dp
                )
            )
        }
    }
}



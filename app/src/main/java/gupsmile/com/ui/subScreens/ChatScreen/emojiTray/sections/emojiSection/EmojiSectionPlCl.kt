package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.EmojiTrayHeightDp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import gupsmile.com.ui.subScreens.ChatScreen.UiStateScreen


@Composable
fun EmojiSectionPlCl(
    emojiSectionViewModel: EmojiSectionViewModel = hiltViewModel(),
    emojiTrayUiState: EmojiTrayHeightDp,
    screenUiState: UiStateScreen
){

    val emojiListtUiState by emojiSectionViewModel.uiStateListEmoji.collectAsState()
    val emojiSearchResults by emojiSectionViewModel.emojisSearchResults.collectAsState()


    EmojiTray(
        emojiTrayUiState = emojiTrayUiState,
        emojiList = emojiListtUiState,
        emojiSearchResults = emojiSearchResults,
        screenUiState = screenUiState

    )
}
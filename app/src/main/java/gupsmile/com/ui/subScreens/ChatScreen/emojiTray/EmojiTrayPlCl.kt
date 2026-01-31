package gupsmile.com.ui.subScreens.ChatScreen.emojiTray

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gupsmile.com.ui.subScreens.ChatScreen.ChatScreenMode
import gupsmile.com.ui.subScreens.ChatScreen.EmojiSearchMode.Active
import gupsmile.com.ui.subScreens.ChatScreen.UiStateScreen
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.DynamicEmojiTray
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.GifTray
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.StickerTray
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection.CategoryHeader
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection.EmojiGridItem
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection.EmojiItem
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection.EmojiSectionPlCl
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection.EmojiSectionViewModel
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection.GridEmojis
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmojiTrayPlCl(
    modifier: Modifier = Modifier,
    emojiTrayViewModel: EmojiTrayViewModel = hiltViewModel(),
    horizontalPagerViewModel: HorizontalPagerViewModel = hiltViewModel(),
    isEmojiOpen: Boolean,
    secondFocusRequester: FocusRequester,
    screenUiState: UiStateScreen,
    searchAction: () -> Unit = {},
    focusStateInputBar: (Boolean) -> Unit = {},
    pagerState: PagerState,
    emojiSectionViewModel: EmojiSectionViewModel = hiltViewModel()

){

    val textUserSearch by emojiSectionViewModel.searchQuery.collectAsState()


    val emojiTrayUiState by emojiTrayViewModel.uiState.collectAsState()
    val uiStateHorizontalPager = horizontalPagerViewModel.uiState.collectAsState()

    val sectionsUiState by emojiTrayViewModel.uiStateEmojiTray.collectAsState()


    val selectedTableIndex = remember{
        derivedStateOf{pagerState.currentPage}
    }

    val scope = rememberCoroutineScope()

    horizontalPagerViewModel.updateNamePage(newValue = pagerState.currentPage)


    val searchModeEmojiTray =
        when(val mode = screenUiState.mode){
        is ChatScreenMode.EmojiTray ->
            mode.searchMode is Active
        else -> false
    }


    val heightTray =
       when{
           isEmojiOpen &&  !searchModeEmojiTray -> 302.dp
           isEmojiOpen &&  searchModeEmojiTray -> 402.dp
           else -> 0.dp
       }


    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .padding(top= 8.dp)
                .fillMaxWidth()
                .height(heightTray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {

            Box(modifier = modifier
                .width(30.dp)
                .height(5.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
            )
            Spacer(modifier = modifier.height(10.dp))


            MainMenuTray(
                contentTabRow = {
                    SectionsHorizontalPager.entries.forEachIndexed { index, section ->
                        EmojiTrayTab(
                            section = section,
                            selected = selectedTableIndex.value == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(section.ordinal)
                                }
                            }
                        )
                    }
                },
                searchAction = searchAction
            )


            when(val mode = screenUiState.mode){
                is ChatScreenMode.EmojiTray -> {
                    if(mode.searchMode is Active){
                        SearchTrayBar(
                            textUser = textUserSearch,
                            ontextchage = emojiSectionViewModel::updateSearchQuery ,
                            onDoneClicked  = {} ,
                            focusRequester = secondFocusRequester,
                            focusStateInputBar = focusStateInputBar,
                            screenUiState = screenUiState,
                        )

                    }
                    HorizontalPager(
                        state = pagerState
                    ) {page ->
                        when(page){
                            0 -> {
                                EmojiSectionPlCl(
                                    emojiTrayUiState = emojiTrayUiState,
                                    screenUiState = screenUiState
                                )
                            }
                            1 -> {
                                DynamicEmojiTray()
                            }
                            2 -> {
                                GifTray()
                            }
                            3 -> {
                                StickerTray()
                            }
                        }
                    }
                } else -> Unit
            }
        }
    }





}

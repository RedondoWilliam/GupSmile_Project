package gupsmile.com.ui.subScreens.ChatScreen.utils

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gupsmile.com.ui.subScreens.ChatScreen.ChatScreenMode
import gupsmile.com.ui.subScreens.ChatScreen.EmojiSearchMode
import gupsmile.com.ui.subScreens.ChatScreen.EmojiSearchMode.Active
import gupsmile.com.ui.subScreens.ChatScreen.FocusTarget
import gupsmile.com.ui.subScreens.ChatScreen.UiStateScreen
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.EmojiTrayHeightDp
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.EmojiTrayViewModel
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.UiStateEmojiTray

@Composable
fun EmojiTraySyncedWithKeyboard(
    isEmojiOpen : Boolean,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    uiState : EmojiTrayHeightDp,
    sectionsUiState: UiStateEmojiTray,
    screenUiState: UiStateScreen,

){
    val density = LocalDensity.current
    val imeHeight = WindowInsets.ime.getBottom(density)



    val searchModeEmojiTray by remember {
        derivedStateOf {
            when(val mode = screenUiState.mode){
                is ChatScreenMode.EmojiTray ->
                   mode.searchMode is Active
                else -> false
            }
        }
    }



    val screenConfiguration = LocalConfiguration.current

    val isPortrait =
        screenConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT
    val extraHeight = if(isEmojiOpen && searchModeEmojiTray ) 150.dp else 0.dp

    val customHeight = if(isPortrait){
        uiState.heightEmojiTrayPortrait
    }else{
        uiState.heightEmojiTrayLandscape
    }

    val trayHeight =
        if(isEmojiOpen){
        val imeDp = with(density){imeHeight.toDp()}

        (customHeight + extraHeight)*(1f - (imeDp / (customHeight + extraHeight)).coerceIn(0f,1f))
        }else{0.dp}

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(if(isEmojiOpen) 302.dp + extraHeight else 0.dp)
            .windowInsetsPadding(WindowInsets.ime)
    ) {
        content()
    }
}
package gupsmile.com.ui.subScreens.ChatScreen.utils

import android.content.res.Configuration
import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.EmojiTrayViewModel

/**
 * A utility Composable that captures the height of the software keyboard (IME)
 * and saves it to a [EmojiTrayViewModel]. This allows other components, like an
 * emoji tray, to use this saved height to mimic the keyboard's dimensions,
 * ensuring a seamless transition between the keyboard and the tray.
 *
 * This function only captures the height when the keyboard is visible (`WindowInsets.isImeVisible`).
 * It differentiates between portrait and landscape orientations, as the keyboard
 * height can vary between them, and saves the value accordingly in the ViewModel.
 *
 * @param emojiTrayViewModel The ViewModel instance used to persist the captured
 * keyboard height for both portrait and landscape modes.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SaveEmojiTrayHeight(
    emojiTrayViewModel: EmojiTrayViewModel
){
    var maxImeHeightPx by remember{ mutableIntStateOf(0) }
    val currentImeHeightPx  = WindowInsets.ime.getBottom(density = LocalDensity.current)
    val density = LocalDensity.current
    //val navBarPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    val screenConfiguration = LocalConfiguration.current
    if(currentImeHeightPx == maxImeHeightPx){
        if(WindowInsets.isImeVisible && maxImeHeightPx != 0){
            if(screenConfiguration.orientation ==Configuration.ORIENTATION_PORTRAIT){
                emojiTrayViewModel.saveHeightEmojiTrayPortrait(
                    heightEmojiTrayPortrait =
                        with(density){maxImeHeightPx.toDp().value  }.toString()
                )
            }
            if(screenConfiguration.orientation ==Configuration.ORIENTATION_LANDSCAPE) {
                emojiTrayViewModel.saveHeightEmojiTrayLandscape(
                    heightEmojiTrayLandscape =with(density){maxImeHeightPx.toDp().value}.toString()
                )
            }
        }
    }else if(currentImeHeightPx > maxImeHeightPx){
        maxImeHeightPx = currentImeHeightPx
    }
}


@Composable
fun isKeyboardVisible(): Boolean{
    val view = LocalView.current
    var isVisible by remember { mutableStateOf(false) }

    DisposableEffect(view){
        val listener = ViewTreeObserver.OnGlobalLayoutListener{
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            isVisible = keypadHeight > screenHeight*0.15
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }

    }
    return isVisible
}
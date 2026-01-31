package gupsmile.com.ui.subScreens.ChatScreen.emojiTray

import android.util.Log
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.ui.subScreens.ChatScreen.data.ChatPreferencesRepository
import gupsmile.com.ui.subScreens.ChatScreen.data.ChatPreferencesRepository.Companion.TAG
import gupsmile.com.ui.subScreens.ChatScreen.data.EmojiTrayHeights
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import javax.inject.Inject


/**
 * ViewModel responsible for managing the state and persistence of the Emoji Tray's dimensions.
 *
 * This ViewModel interacts with [ChatPreferencesRepository] to save and retrieve the preferred
 * heights for the emoji tray in both portrait and landscape orientations. It exposes the current
 * height values as a UI state that can be observed by composables.
 *
 * @property chatPreferencesRepository The repository used for persisting and retrieving emoji tray height settings.
 *           Injected by Hilt.
 */
@HiltViewModel
class EmojiTrayViewModel @Inject constructor(
    private val chatPreferencesRepository: ChatPreferencesRepository
): ViewModel() {


    private val _uiStateEmojiTray = MutableStateFlow(UiStateEmojiTray())
    val uiStateEmojiTray: StateFlow<UiStateEmojiTray> = _uiStateEmojiTray

    fun updateSectionTray(section: SectionEmojiTray){
        _uiStateEmojiTray.value = _uiStateEmojiTray.value.copy(
            sectionTray = section
        )
    }

    fun updateSearchMode(searchMode: Boolean){
        _uiStateEmojiTray.value = _uiStateEmojiTray.value.copy(
            searchMode = searchMode
        )
    }



    /**
     * A [StateFlow] representing the current UI state for the emoji tray heights.
     *
     * This flow listens to changes in the [ChatPreferencesRepository] and maps the stored raw values
     * into a [EmojiTrayHeightDp] object, converting them to [Dp] units. This allows the UI to
     * reactively update whenever the persisted height values change.
     *
     * The flow is configured to remain active for 5 seconds after the last subscriber is gone,
     * which helps maintain the state across brief configuration changes. An initial default
     * value is provided for the first emission.
     */
    val uiState: StateFlow<EmojiTrayHeightDp> =
        chatPreferencesRepository
            .heightEmojiTray
            .map {
                EmojiTrayHeightDp(
                    heightEmojiTrayLandscape = it.heightEmojiTrayLandscape.toFloat().dp,
                    heightEmojiTrayPortrait = it.heightEmojiTrayPortrait.toFloat().dp
                )

            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = EmojiTrayHeightDp(
                    heightEmojiTrayLandscape = 200.dp,
                    heightEmojiTrayPortrait = 302.dp,
                )
            )

    /**
     * Saves the height of the emoji tray for the landscape orientation.
     *
     * This function is executed within the [viewModelScope] to ensure it is lifecycle-aware.
     *
     * @param heightEmojiTrayLandscape The height value to save, as a [String].
     */
    fun saveHeightEmojiTrayLandscape(heightEmojiTrayLandscape: String){
        viewModelScope.launch {
            chatPreferencesRepository.saveHeightEmojiTrayLandscape(heightEmojiTrayLandscape)
        }
    }

    /**
     * Saves the height of the emoji tray for the portrait orientation.
     *
     * This function is executed within the [viewModelScope] to ensure it is lifecycle-aware.
     *
     * @param heightEmojiTrayPortrait The height value to save, as a [String].
     */
    fun saveHeightEmojiTrayPortrait(heightEmojiTrayPortrait: String){
        viewModelScope.launch {
            chatPreferencesRepository.saveHeightEmojiTrayPortrait(heightEmojiTrayPortrait)
        }
    }



}


data class UiStateEmojiTray(
    val sectionTray: SectionEmojiTray = SectionEmojiTray.EMOJISECTION,
    val searchMode: Boolean = false

)

enum class SectionEmojiTray{
    EMOJISECTION,
    STICKERSECTION,
    GIFSECTION,
    DYNAMICEMOJISECTION
}


/**
 * Data class representing the UI state for the emoji tray's height in different orientations.
 *
 * This class holds the height values as [Dp] units, which can be directly used by Jetpack Compose
 * modifiers.
 *
 * @property heightEmojiTrayLandscape The height of the emoji tray for landscape mode.
 * @property heightEmojiTrayPortrait The height of the emoji tray for portrait mode.
 */
data class EmojiTrayHeightDp(
    val heightEmojiTrayLandscape: Dp,
    val heightEmojiTrayPortrait: Dp,
)








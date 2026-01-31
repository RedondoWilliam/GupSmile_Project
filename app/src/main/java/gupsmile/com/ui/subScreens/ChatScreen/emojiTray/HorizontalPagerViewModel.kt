package gupsmile.com.ui.subScreens.ChatScreen.emojiTray

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import gupsmile.com.R


@HiltViewModel
class HorizontalPagerViewModel @Inject constructor(
): ViewModel() {

    private val _uiState = MutableStateFlow(UiStateHorizontalPager())
    val uiState: StateFlow<UiStateHorizontalPager> = _uiState

    private fun updateStateHorizontalPager(page: SectionsHorizontalPager){
        _uiState.value = _uiState.value.copy(
            stateHorizontalPager = page
        )
    }

    fun updateNamePage(newValue: Int){
        _uiState.value = _uiState.value.copy(
            nameSection = newValue
        )
        assignedPageTopNavigation()
    }

    private fun assignedPageTopNavigation(){
        when(uiState.value.nameSection){
            0 -> updateStateHorizontalPager(page = SectionsHorizontalPager.EmojiTray)
            1 -> updateStateHorizontalPager(page = SectionsHorizontalPager.DynamicEmojiTray)
            2 -> updateStateHorizontalPager(page = SectionsHorizontalPager.StickerTray)
            3 -> updateStateHorizontalPager(page = SectionsHorizontalPager.GifTray)
        }
    }


}



enum class SectionsHorizontalPager(
    val icon: Int,
    val contentDescription: String
){
    EmojiTray(R.drawable.emojimenuicon,"EmojiTray"),
    DynamicEmojiTray(R.drawable.dinamicemojiicon,"DynamicEmojiTray"),
    StickerTray(R.drawable.stickersicon,"StickerTray"),
    GifTray(R.drawable.gificon,"GifTray")
}

data class UiStateHorizontalPager(
    var stateHorizontalPager: SectionsHorizontalPager = SectionsHorizontalPager.EmojiTray,
    var nameSection: Int = 1,
)



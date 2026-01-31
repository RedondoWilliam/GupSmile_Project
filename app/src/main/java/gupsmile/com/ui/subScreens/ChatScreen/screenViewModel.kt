package gupsmile.com.ui.subScreens.ChatScreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection.EmojiGridItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class ScreenViewModel @Inject constructor(

): ViewModel(){

    private val _uiState = MutableStateFlow(UiStateScreen())
    val uiState: StateFlow<UiStateScreen> = _uiState


    init {
        setDefaultMode()
    }


    fun setDefaultMode(){
        updateMode(ChatScreenMode.Default)
    }

    fun startTyping(){
        updateMode(ChatScreenMode.Typing)
    }

    fun startAudioRecording(){
        updateMode(ChatScreenMode.TakingAudio)
    }

    fun openEmojiTray(
        section: SectionEmojiTray = SectionEmojiTray.EMOJI_SECTION,
        searchMode: EmojiSearchMode = EmojiSearchMode.None
    ){
        updateMode(
            ChatScreenMode.EmojiTray(
                sectionMode = section,
                searchMode = searchMode
            )
        )
    }


    fun toggleEmojiTraySearchMode(){
        _uiState.update { state ->
            when(val mode = state.mode){
                is ChatScreenMode.EmojiTray -> {
                    val toggleSearchMode =
                        if(mode.searchMode is EmojiSearchMode.Active)
                            EmojiSearchMode.None
                        else
                            EmojiSearchMode.Active
                    state.copy(
                        mode = mode.copy(searchMode = toggleSearchMode)
                    )
                }
                else -> state
            }
        }
    }

    fun openAddElement(mode: AddElementMode){
        updateMode(ChatScreenMode.AddElement(mode))
    }


    private fun updateMode(mode: ChatScreenMode) {
        _uiState.value = _uiState.value.copy(
            mode = mode
        )
    }



    fun pageToSection(page: Int): SectionEmojiTray =
        when(page){
            0 -> SectionEmojiTray.EMOJI_SECTION
            1 -> SectionEmojiTray.DYNAMIC_EMOJI_SECTION
            2 -> SectionEmojiTray.GIF_SECTION
            3 -> SectionEmojiTray.STICKER_SECTION
            else -> SectionEmojiTray.EMOJI_SECTION
        }


    fun onEmojiTraySectionChanged(section: SectionEmojiTray){
        _uiState.update { state ->
            when(val mode = state.mode){
                is ChatScreenMode.EmojiTray -> {
                    state.copy(
                        mode = mode.copy(sectionMode = section)
                    )
                }
                else -> state
            }
        }
    }

}



data class UiStateScreen(
    val mode: ChatScreenMode = ChatScreenMode.Default
)




sealed class ChatScreenMode{
    data object Default: ChatScreenMode()
    data object Typing: ChatScreenMode()
    data object TakingAudio: ChatScreenMode()
    data class EmojiTray(
        val searchMode: EmojiSearchMode = EmojiSearchMode.None,
        val sectionMode: SectionEmojiTray = SectionEmojiTray.EMOJI_SECTION
    ): ChatScreenMode()
    data class AddElement(
        val mode: AddElementMode
    ): ChatScreenMode()

}

sealed class EmojiSearchMode{
    data object None: EmojiSearchMode()
    data object Active: EmojiSearchMode()
}

enum class SectionEmojiTray{
    EMOJI_SECTION,
    STICKER_SECTION,
    GIF_SECTION,
    DYNAMIC_EMOJI_SECTION
}

sealed class AddElementMode{
    data object AddContact: AddElementMode()
    data object AddLocation: AddElementMode()
    data object Galery: AddElementMode()
    data object AddFile: AddElementMode()
    data object Camera: AddElementMode()
}


package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.room.Entity
import androidx.room.PrimaryKey
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji.Emoji
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji.EmojiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EmojiSectionViewModel @Inject constructor(
    private val emojiRepository: EmojiRepository
): ViewModel(){


    val uiStateListEmojis: Flow<PagingData<Emoji>> = Pager(
        config = PagingConfig(pageSize = 150, enablePlaceholders = false, initialLoadSize = 200),
        pagingSourceFactory = {
            emojiRepository.getAllEmojis()
        }
    )
        .flow
        .cachedIn(viewModelScope)


    val uiStateListEmoji: StateFlow<List<EmojiGridItem>> =
        emojiRepository.getAllEmoji()
            .map { emojis ->
                buildEmojiGridItems(emojis)
            }
            .stateIn(
                scope = viewModelScope,
                started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )




    fun getEmoji(emojiId: Int)= emojiRepository.getEmojiStream(emojiId)

    suspend  fun insertEmoji(emoji: Emoji) = emojiRepository.insertEmoji(emoji)


    init {
        viewModelScope.launch {
            emojiRepository.ensureEmojisLoaded()
        }
    }






    /**
     * Holds the current text entered in the emoji search bar.
     *
     * This StateFlow is the single source of truth for search state.
     * Whenever its value changes, all dependent flows react automatically.
     */
     val searchQuery = MutableStateFlow("")



    /**
     * Emits the list of emojis to display, depending on the current search query.
     *
     * Behavior:
     * - When the query is blank:
     *   → emits the full emoji list.
     * - When the query is not blank:
     *   → emits only emojis whose keywords match the query.
     *
     * Operators:
     * - debounce(200):
     *   Prevents triggering database queries on every keystroke.
     * - distinctUntilChanged():
     *   Avoids duplicate queries for the same input.
     * - flatMapLatest():
     *   Cancels the previous search when a new query arrives.
     *
     * This Flow always emits List<EmojiDetails>.
     */
    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val emojisSearchResults: StateFlow<List<EmojiDetails>> =
        searchQuery
            .debounce(200)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                if(query.isBlank()){
                    emojiRepository.getRandomEmojis()
                }else{
                    emojiRepository.searchEmojis(query)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()

            )





    fun updateSearchQuery(query: String){
        searchQuery.value = query
    }








}

data class EmojiSectionUiState(
    val emojiDetails: EmojiDetails = EmojiDetails()
)


data class  EmojiDetails(
    val id: Int = 0,
    val imageUrl: String ="",
    val path: String = "",
    val isRecent: Boolean = false,
    val category: String = "",
    val unicodeName: String = "",
    val keywords: String = "",
    )

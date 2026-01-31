package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji

import androidx.paging.PagingSource
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection.EmojiDetails
import kotlinx.coroutines.flow.Flow


interface EmojiRepository {

    fun getAllEmojis(): PagingSource<Int, Emoji>

    fun getAllEmoji(): Flow<List<EmojiDetails>>

    fun getEmojiStream(id: Int): Flow<Emoji?>

    suspend fun insertEmoji(emoji: Emoji)

    suspend fun ensureEmojisLoaded()

    fun searchEmojis(query: String): Flow<List<EmojiDetails>>

    fun getRandomEmojis():Flow<List<EmojiDetails>>

}
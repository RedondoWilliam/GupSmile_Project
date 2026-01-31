package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji

import androidx.paging.PagingSource
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji.json.EmojiJsonDataSource
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji.json.toEntity
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection.EmojiDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class OffLineEmojiRepository @Inject constructor(
    private val emojiDao: EmojiDao,
    private val jsonDataSource: EmojiJsonDataSource
) : EmojiRepository {

    override fun getAllEmoji(): Flow<List<EmojiDetails>>  = emojiDao.getAllEmoji()

    override fun getEmojiStream(id: Int): Flow<Emoji?> = emojiDao.getEmoji(id)

    override fun getAllEmojis(): PagingSource<Int, Emoji>  = emojiDao.getAllEmojisStream()

    override suspend fun insertEmoji(emoji: Emoji) = emojiDao.insertEmoji(emoji)

    override suspend fun ensureEmojisLoaded() {
        withContext(Dispatchers.IO) {
            if(emojiDao.countEmojis() > 0) return@withContext

            val emojisFromJson = jsonDataSource.loadEmojis()
            val entities = emojisFromJson.map {
                it.toEntity()
            }
            emojiDao.insertAll(entities)

        }
    }

    override fun searchEmojis(query: String): Flow<List<EmojiDetails>>  = emojiDao.searchEmojis(query)


    override fun getRandomEmojis(): Flow<List<EmojiDetails>> = emojiDao.getRandomEmojis()
}
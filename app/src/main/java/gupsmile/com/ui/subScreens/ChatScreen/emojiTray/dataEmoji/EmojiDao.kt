package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection.EmojiDetails
import kotlinx.coroutines.flow.Flow


@Dao
interface EmojiDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEmoji(emoji: Emoji)

    @Query("SELECT * FROM emojis WHERE id = :id")
    fun getEmoji(id: Int): Flow<Emoji>

    @Query("SELECT * FROM emojis ORDER BY id ASC")
    fun getAllEmojisStream(): PagingSource<Int, Emoji>


    @Query("SELECT * FROM emojis ORDER BY category, id ASC")
    fun getAllEmoji(): Flow<List<EmojiDetails>>


    @Query("""
    SELECT *
    FROM emojis
    WHERE LOWER(keywords) LIKE '%' || LOWER(:query) || '%'
    ORDER BY category, id ASC
    """)
    fun searchEmojis(query: String): Flow<List<EmojiDetails>>



    @Query("SELECT COUNT(*) FROM emojis")
    suspend fun countEmojis(): Int


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(emojis: List<Emoji>)


    @Query("SELECT * FROM emojis ORDER BY RANDOM() LIMIT 96")
    fun getRandomEmojis(): Flow<List<EmojiDetails>>


}
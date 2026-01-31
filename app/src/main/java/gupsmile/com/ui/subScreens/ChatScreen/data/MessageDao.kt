package gupsmile.com.ui.subScreens.ChatScreen.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMessage(message: Message)

    @Update
    suspend fun updateMessage(message: Message)

    @Delete
    suspend fun deleteMessage(message: Message)

    @Query("DELETE FROM messages WHERE id IN (:ids)")
    suspend fun deleteMessages(ids: List<Int>)

    @Query("SELECT * FROM messages WHERE id = :id")
    fun getMessage(id: Int): Flow<Message>

    @Query("SELECT * FROM messages ORDER BY id DESC")
    fun getAllMessagesStream(): PagingSource<Int, Message>


}
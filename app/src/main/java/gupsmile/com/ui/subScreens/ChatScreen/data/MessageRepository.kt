package gupsmile.com.ui.subScreens.ChatScreen.data

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow


interface MessageRepository  {

    fun getAllMessages(): PagingSource<Int, Message>


    fun getMessageStream(id: Int): Flow<Message?>

    suspend fun insertMessage(message: Message)

    suspend fun deleteMessage(message: Message)

    suspend fun updateMessage(message: Message)

    suspend fun deleteMessages(ids: List<Int>)

}
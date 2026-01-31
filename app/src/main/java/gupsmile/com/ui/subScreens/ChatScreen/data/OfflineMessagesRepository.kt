package gupsmile.com.ui.subScreens.ChatScreen.data

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow





class OfflineMessagesRepository(private val messageDao: MessageDao) : MessageRepository {


    override fun getMessageStream(id: Int): Flow<Message?> = messageDao.getMessage(id)

    override suspend fun insertMessage(message: Message) = messageDao.insertMessage(message)

    override suspend fun deleteMessage(message: Message) = messageDao.deleteMessage(message)

    override suspend fun updateMessage(message: Message) = messageDao.updateMessage(message)

    override fun getAllMessages(): PagingSource<Int, Message> = messageDao.getAllMessagesStream()

    override suspend fun deleteMessages(ids: List<Int>) = messageDao.deleteMessages(ids)



}
package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji

import android.content.Context
import androidx.room.ProvidedTypeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gupsmile.com.ui.subScreens.ChatScreen.data.OfflineMessagesRepository
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji.json.EmojiJsonDataSource
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ModulesEmojis {


    @Provides
    @Singleton
    fun provideEmojiDataBase(
        @ApplicationContext context: Context,
    ): EmojiDatabase =
        EmojiDatabase.getDatabase(context)


    @Provides
    @Singleton
    fun provideEmojiDao(
        emojiDatabase: EmojiDatabase): EmojiDao
        = emojiDatabase.emojiDao()



    @Singleton
    @Provides
    fun provideEmojiRepository(
        emojiDao: EmojiDao,
        emojiJsonDataSource: EmojiJsonDataSource
    ): EmojiRepository{
        return OffLineEmojiRepository(
            emojiDao = emojiDao,
            emojiJsonDataSource
        )
    }
}
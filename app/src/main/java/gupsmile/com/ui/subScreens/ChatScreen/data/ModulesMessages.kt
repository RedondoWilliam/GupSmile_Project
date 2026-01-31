package gupsmile.com.ui.subScreens.ChatScreen.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ModulesMessages{


    @Singleton
    @Provides
    fun ProvideMessageRepositoryInstance(
        @ApplicationContext context: Context
    ): MessageRepository{
        return OfflineMessagesRepository(MessagesDatabase.getDatabase(context).messageDao())
    }




    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext appContext: Context): DataStore<Preferences>  =
        PreferenceDataStoreFactory.create(
            produceFile = { appContext.preferencesDataStoreFile("chat_preferences") }
        )


//    @Provides
//    @Singleton
//    fun provideChatPreferencesRepository(
//        dataStore: DataStore<Preferences>
//    ): ChatPreferencesRepository {
//        return ChatPreferencesRepository( dataStore)
//    }
}

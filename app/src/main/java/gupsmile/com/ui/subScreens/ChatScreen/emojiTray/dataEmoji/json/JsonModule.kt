package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji.json

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object JsonModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi  =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


    @Provides
    @Singleton
    fun provideEmojiJsonDataSource(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): EmojiJsonDataSource = EmojiJsonDataSource(context, moshi)


}

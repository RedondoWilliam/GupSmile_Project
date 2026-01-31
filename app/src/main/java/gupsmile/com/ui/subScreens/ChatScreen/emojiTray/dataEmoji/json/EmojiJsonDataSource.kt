package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji.json

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject



private const val EMOJI_JSON_PATH = "emojis/emojis.json"


class EmojiJsonDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val moshi: Moshi
) {
    suspend fun loadEmojis(): List<EmojiJsonDto> =
        withContext(Dispatchers.IO) {
            val json = context.assets
                .open(EMOJI_JSON_PATH)
                .bufferedReader()
                .use { it.readText() }

            val type = Types.newParameterizedType(
                List::class.java,
                EmojiJsonDto::class.java
            )
            val adapter = moshi.adapter<List<EmojiJsonDto>>(type)

            adapter.fromJson(json).orEmpty()
        }
}
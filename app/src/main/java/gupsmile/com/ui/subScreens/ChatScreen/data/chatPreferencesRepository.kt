package gupsmile.com.ui.subScreens.ChatScreen.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ChatPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    internal companion object {
        val HEIGHT_EMOJI_TRAY_LANDSCAPE = stringPreferencesKey("height_emojiTray_landscape")
        val HEIGHT_EMOJI_TRAY_PORTRAIT = stringPreferencesKey("height_emojiTray_portrait")
        const val TAG = "UserPreferencesChatRepo"
    }

    val heightEmojiTray: Flow<EmojiTrayHeights> = dataStore.data
        .catch { exception ->
           if(exception is java.io.IOException){
               Log.e(TAG, "Error reading preferences.", exception)
               emit(emptyPreferences())
           } else {
               throw exception
           }
        }
        .map { preferences ->
            EmojiTrayHeights(
                heightEmojiTrayLandscape = preferences[HEIGHT_EMOJI_TRAY_LANDSCAPE] ?: "200",
                heightEmojiTrayPortrait = preferences[HEIGHT_EMOJI_TRAY_PORTRAIT] ?: "302"
            )
        }

    suspend fun saveHeightEmojiTrayLandscape(height: String) {
        dataStore.edit { preferences ->
            Log.d(TAG, "saveHeightEmojiTrayLandscape: $height")
            preferences[HEIGHT_EMOJI_TRAY_LANDSCAPE] = height
        }
    }
    suspend fun saveHeightEmojiTrayPortrait(height: String) {
        dataStore.edit { preferences ->
            Log.d(TAG, "saveHeightEmojiTrayPortrait: $height")
            preferences[HEIGHT_EMOJI_TRAY_PORTRAIT] = height
        }
    }

    suspend fun clearPreferences() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

data class EmojiTrayHeights(
    val heightEmojiTrayLandscape: String,
    val heightEmojiTrayPortrait: String
)
package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Emoji::class], version = 21, exportSchema = false)
abstract class EmojiDatabase: RoomDatabase() {

    abstract fun emojiDao(): EmojiDao

    companion object {

        @Volatile
        private var Instance: EmojiDatabase? = null

        fun getDatabase(context: Context): EmojiDatabase {
            return Instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context, EmojiDatabase::class.java, "emoji_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}
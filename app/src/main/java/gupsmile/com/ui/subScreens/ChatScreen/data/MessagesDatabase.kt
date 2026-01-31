package gupsmile.com.ui.subScreens.ChatScreen.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Message::class], version = 2, exportSchema = false)
abstract class MessagesDatabase: RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {

        @Volatile
        private var Instance: MessagesDatabase? = null

        fun getDatabase(context: Context): MessagesDatabase {
            return Instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context, MessagesDatabase::class.java, "messages_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}
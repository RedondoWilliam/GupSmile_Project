package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "emojis")
data class Emoji(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imageUrl: String ="",
    val path: String,
    val isRecent: Boolean = false,
    val category: String,
    val unicodeName: String,
    val keywords: String,

)
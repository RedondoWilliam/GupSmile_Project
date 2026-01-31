package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji.json


import android.R.string
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class EmojiJsonDto(

    @Json(name = "unicode_name")
    val unicodeName: String,

    val path: String,
    val keywords: List<String>,
    val category: String
)
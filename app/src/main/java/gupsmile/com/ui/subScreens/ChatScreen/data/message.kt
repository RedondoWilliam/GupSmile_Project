package gupsmile.com.ui.subScreens.ChatScreen.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "messages")
data class Message(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val senderId: String,
    val receiverId: String,
    val text: String,
    val timestamp: Long,
    val formatterDateTime: String = ""
)

fun Long.toFormattedTime(): String {
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return  formatter.format(Date(this))
}

fun Long.toFormattedDateTime(): String {
    val formatter = SimpleDateFormat("MMMM d, yyyy", Locale("es", "ES"))
    return  formatter.format(Date(this))
}

fun Long.toFormattedDateWeekDay(): String {
    val formatter = SimpleDateFormat("EEEE", Locale("es", "ES"))
    return  formatter.format(Date(this))
}
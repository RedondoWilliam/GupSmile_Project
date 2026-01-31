package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji.json

import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.dataEmoji.Emoji

fun EmojiJsonDto.toEntity(): Emoji =
    Emoji(
        unicodeName = unicodeName,
        path = path,
        keywords = keywords.joinToString(","),
        category = category,
    )
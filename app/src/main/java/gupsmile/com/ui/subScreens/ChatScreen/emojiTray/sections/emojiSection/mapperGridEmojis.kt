package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection

sealed interface EmojiGridItem{
    data class Header(val category: String): EmojiGridItem
    data class Emoji(val data: EmojiDetails): EmojiGridItem
}

fun buildEmojiGridItems(emojis: List<EmojiDetails>): List<EmojiGridItem>{

    val categoryOrderMap = CATEGORY_ORDER
        .withIndex()
        .associate { it.value to it.index }


    return emojis
        .groupBy { it.category }
        .toList()
        .sortedBy { (category, _) ->
            categoryOrderMap[category] ?: Int.MAX_VALUE
        }
        .flatMap { (category,items) ->
            listOf(EmojiGridItem.Header(category)) + items.map { EmojiGridItem.Emoji(it) }
        }
}


val CATEGORY_ORDER = listOf(
    "Emojis and people",
    "Gestures",
    "People",
    "Animals",
    "Nature",
    "Food and drinks",
    "Activities",
    "Objects",
    "Travels and places",
    "Symbols",
    "Letters and clock",
    "Flags",
)

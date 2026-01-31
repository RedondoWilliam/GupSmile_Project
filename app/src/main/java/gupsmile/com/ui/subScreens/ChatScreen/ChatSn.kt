package gupsmile.com.ui.subScreens.ChatScreen

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.toolingGraphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.common.collect.Multimaps.index
import gupsmile.com.ui.commonElements.VerticalLazyColumnScrollBar
//import androidx.paging.compose.LazyPagingItems
import gupsmile.com.ui.subScreens.ChatScreen.data.Message
import gupsmile.com.ui.theme.GupsMileTheme
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import gupsmile.com.R
import gupsmile.com.ui.subScreens.ChatScreen.data.toFormattedDateTime
import gupsmile.com.ui.subScreens.ChatScreen.data.toFormattedTime

/**
 * Represents the various states of the chat screen.
 *
 *
 * @property globalActionsState Describes the current state of message entries, e.g., if a new message was just added.
 * @property enabled Whether the text input and send button are enabled.
 */
data class ChatScreenState(
    val globalActionsState: UiStateGlobalMessages = UiStateGlobalMessages(),
    val enabled: Boolean = true,
    val hasFocus: Boolean = false,

)

/**
 * Defines the set of actions (callbacks) that can be triggered from the chat screen.
 * These are typically functions implemented in a ViewModel or a parent Composable.
 *
 * @property onTextChange Callback invoked when the user types in the message input field.
 *                      Parameter is the new text string.
 * @property onDoneClicked Callback invoked when the user presses the send/done button on the keyboard
 *                         or a dedicated send button.
 * @property onChatClicked Callback invoked when a chat message bubble is clicked.
 *                         Parameter is the [Message] object that was clicked, or null.
 * @property onLongPress Callback invoked when a chat message bubble is long-pressed.
 *                       Parameter is the [Message] object that was long-pressed, or null.
 * @property resetListEntriesState Callback to reset the [ChatScreenState.globalActionsState] to its default,
 *                                 typically after an effect (like scrolling to a new message) has been consumed.
 */
data class ChatActions(
    val onTextChange: (String) -> Unit = {},
    val onDoneClicked: () -> Unit = {},
    val onChatClicked: (Message?) -> Unit = {},
    val onLongPress: (Message?) -> Unit = {},
    val resetListEntriesState: () -> Unit = {}
)



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    stateListMessages: LazyPagingItems<Message> = previewLazyPagingItems(),
    chatScreenState: ChatScreenState = ChatScreenState(),
    chatActions: ChatActions = ChatActions(),
    selectMessageBarActions: SelectMessageBarActions = SelectMessageBarActions(),
    emojiTrayState: () -> Unit = {}
    ){

    val focusRequester = remember { FocusRequester() }

   Box(
       modifier = Modifier.fillMaxWidth()
   ) {

       val isDarkTheme = isSystemInDarkTheme()
       Image(
           painter = painterResource(
               id = if(isDarkTheme ) R.drawable.circle_scatter_dark
               else R.drawable.circle_scatter_two)
           ,
           modifier = Modifier
               .fillMaxSize()
           ,
           contentScale = ContentScale.Crop,
           contentDescription = "Background",
           alpha = 0.05f

       )

       Column(
           verticalArrangement = Arrangement.SpaceBetween,
           modifier = modifier
               .fillMaxSize()
       ) {


           ChatList(
               modifier = modifier
                   .wrapContentSize()
                   .weight(1f),
               stateListMessages = stateListMessages,
               onLongPress = {chatActions.onLongPress(it)},
               onChatClicked = {chatActions.onChatClicked(it)},
               resetListEntriesState = chatActions.resetListEntriesState,
               chatScreenState = chatScreenState,
           )
//           TextBarChat(
//               textUser = chatScreenState.globalActionsState.messageUiState.messageDetails.text,
//               ontextchage = chatActions.onTextChange ,
//               onDoneClicked  = chatActions.onDoneClicked,
//               focusRequester = focusRequester,
//               enabled = chatScreenState.enabled,
//               emojiTrayState = emojiTrayState
//           )
       }
   }
}



@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun ChatList(
    modifier: Modifier = Modifier,
    onChatClicked: (Message?) -> Unit = {},
    onLongPress: (Message?) -> Unit = {},
    stateListMessages: LazyPagingItems<Message> = previewLazyPagingItems(),
    resetListEntriesState: () -> Unit = {},
    chatScreenState: ChatScreenState = ChatScreenState(),
){

    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val showScrollButton = remember{ mutableStateOf(false) }


    val overlayColor = MaterialTheme.colorScheme.surfaceTint.copy(0.3f)




    LaunchedEffect(lazyListState.isScrollInProgress ) {
        snapshotFlow {
            lazyListState.firstVisibleItemIndex to  lazyListState.layoutInfo.visibleItemsInfo.size
        }.collect{
                val visibleFirstIndex = lazyListState.layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0
                showScrollButton.value = visibleFirstIndex > 4
            }
    }

    LaunchedEffect(stateListMessages.itemCount) {
       if(chatScreenState.globalActionsState.listEntriesState == UiStateEntries.ADDEDMESSAGE){
           lazyListState.animateScrollToItem(index =0)
           resetListEntriesState()
       }
    }

    Box(
        modifier = modifier
    ) {

        LazyColumn(
            modifier = modifier
                .VerticalLazyColumnScrollBar(
                    lazyListState,
                    visibleState = lazyListState.isScrollInProgress)
                .fillMaxWidth()
                ,
            horizontalAlignment = Alignment.End,
            state = lazyListState,
            reverseLayout = true,
            verticalArrangement = Arrangement.Bottom

        )
        {



            items(
                count = stateListMessages.itemCount,
                key = {index -> stateListMessages[index]?.id ?: index }
            ){

                val currentMessage = stateListMessages[it]
                if (currentMessage == null) {
                    // Handle null case, maybe display a placeholder or skip
                    return@items // or some other handling
                }

                Row(
                    modifier = modifier
                        .drawWithContent{
                            drawContent()
                            if (
                                chatScreenState.globalActionsState.selectionMode
                                && chatScreenState.globalActionsState.selectedMessages.contains(
                                    stateListMessages[it]?.id
                                )
                                ) {
                                drawRect(
                                    color = overlayColor,
                                )
                            }
                        }
                        .fillMaxWidth()
                ) {
                    BubbleChat(
                        message = currentMessage.text,
                        onChatClicked = {
                            onChatClicked(
                                currentMessage
                            )
                        },
                        onLongPress = {
                            onLongPress(
                                currentMessage
                            )
                        },
                        timeMessage = currentMessage.timestamp.toFormattedTime(),
                        )
                }



                if(currentMessage == stateListMessages.itemSnapshotList.lastOrNull()
                    ||   currentMessage.timestamp.toFormattedDateTime()
                    != stateListMessages[it + 1]?.timestamp?.toFormattedDateTime() ){
                    Row(
                        modifier = modifier
                            .padding(top = 7.dp, bottom = 14.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DateSeparator(
                            date =currentMessage.timestamp.toFormattedDateTime().toString(),
                            modifier = Modifier.wrapContentSize()
                        )
                    }
                }
            }

        }

        AnimatedVisibility(
            visible = showScrollButton.value,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
        ) {
            FloatingActionButton(
                onClick = {
                   coroutineScope.launch {
                       lazyListState.scrollToItem(0)
                   }
                },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.85f),
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.85f),
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(
                    5.dp, 10.dp, 0.dp
                ),
                modifier = Modifier
                    .size(35.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardDoubleArrowDown,
                    contentDescription = "Scroll to down",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
        }
    }
}


@Composable
fun previewLazyPagingItems(): LazyPagingItems<Message> {
    val fakeMessages = listOf(
        Message(id = 1, senderId = "", receiverId = "", text = "Hola", timestamp = System.currentTimeMillis(), formatterDateTime = System.currentTimeMillis().toFormattedDateTime()),
        Message(id = 2, senderId = "", receiverId = "", text = "Hola", timestamp = System.currentTimeMillis(), formatterDateTime = System.currentTimeMillis().toFormattedDateTime()),
        Message(id = 3, senderId = "", receiverId = "", text = "Hola", timestamp = System.currentTimeMillis(), formatterDateTime = System.currentTimeMillis().toFormattedDateTime()),
    )

    return  flowOf(PagingData.from(fakeMessages)).collectAsLazyPagingItems()
}


@Preview(
    showBackground = true,
    name = "Date Separator",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    name = "Date Separator Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ChatScreenDarkPreview(){
    GupsMileTheme() {
        ChatScreen(
            stateListMessages = previewLazyPagingItems()
        )
    }
}
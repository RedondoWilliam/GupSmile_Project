//@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package gupsmile.com.ui.subScreens.ChatScreen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import gupsmile.com.ui.subScreens.ChatScreen.EmojiSearchMode.Active
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.EmojiTrayPlCl
import gupsmile.com.ui.subScreens.ChatScreen.emojiTray.EmojiTrayViewModel
import gupsmile.com.ui.subScreens.ChatScreen.utils.SaveEmojiTrayHeight
import gupsmile.com.ui.subScreens.ChatScreen.utils.isKeyboardVisible
import kotlinx.coroutines.launch


enum class  FocusTarget{
    NONE,
    MESSAGE_INPUT,
    EMOJI_SEARCH
}



@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun ChatScreenPanelControl(
    modifier: Modifier = Modifier,
    messageViewModel: MessageViewModel = hiltViewModel(),
    emojiTrayViewModel: EmojiTrayViewModel = hiltViewModel(),
    screenViewModel: ScreenViewModel = hiltViewModel()
    ){


    val screenUiState by screenViewModel.uiState.collectAsState()


    val coroutineScope = rememberCoroutineScope()

    val globalActionsState by messageViewModel.stateActionOnMessagesFlow.collectAsState()

    val stateListMessages = messageViewModel.uiStateListMessages.collectAsLazyPagingItems()

    var emojiTrayState by remember{mutableStateOf(false)}

    val keyboardController = LocalSoftwareKeyboardController.current


    val firstFocusRequester = remember { FocusRequester() }

    val secondFocusRequester = remember{ FocusRequester()}

    val focusManager = LocalFocusManager.current

    var focusTextBarChat by rememberSaveable  { mutableStateOf(false) }

    var focusSearchBarMediaTray by rememberSaveable  { mutableStateOf(false) }


    val focusTarget =
        when(val mode = screenUiState.mode){
            is ChatScreenMode.Typing -> FocusTarget.MESSAGE_INPUT
            is ChatScreenMode.EmojiTray ->
                if(mode.searchMode is Active)
                    FocusTarget.EMOJI_SEARCH
                else
                    FocusTarget.NONE
            else -> FocusTarget.NONE
        }

    LaunchedEffect(focusTarget) {
        when(focusTarget){
            FocusTarget.MESSAGE_INPUT ->
                firstFocusRequester.requestFocus()
            FocusTarget.EMOJI_SEARCH ->
                secondFocusRequester.requestFocus()
            FocusTarget.NONE ->
                focusManager.clearFocus()

        }
    }


    val stateKeyBoard = isKeyboardVisible()





    SaveEmojiTrayHeight(emojiTrayViewModel)

    BackHandler(enabled =  globalActionsState.selectionMode) {
        messageViewModel.clearSelection()
    }
    BackHandler {
        if(screenUiState.mode !is ChatScreenMode.Default){
            screenViewModel.setDefaultMode()
        }
    }


    val chatActionsParam = ChatActions(
        onTextChange = {
            messageViewModel.updateUiState(MessageDetails(text = it))
                       },
        onDoneClicked = {
            coroutineScope.launch {
                messageViewModel.updateListEntriesState(UiStateEntries.ADDEDMESSAGE)
                messageViewModel.saveMessage()
            }},
        onChatClicked = {
            if(  globalActionsState.selectionMode){
                messageViewModel.toggleMessageSelection(it?.id ?: 0)
            }
        },
        onLongPress = {
            messageViewModel.toggleMessageSelection(it?.id ?: 0)
        },
        resetListEntriesState = {
            messageViewModel.updateListEntriesState(UiStateEntries.DEFAULT)
        }
    )

    val chatScreenStateParam = ChatScreenState(
        globalActionsState =  globalActionsState,
        enabled = true,
        hasFocus = focusTextBarChat
    )

    val selectMessageBarActionsParam =
        SelectMessageBarActions(
            trashMessageAction = {
                messageViewModel.updateListEntriesState(UiStateEntries.DELETEMESSAGE)
                messageViewModel.deleteSelectedMessages()
            }
        )

    val interactionSource = remember { MutableInteractionSource() }

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {4}
    )

    LaunchedEffect(pagerState.currentPage) {
        screenViewModel.onEmojiTraySectionChanged(
            screenViewModel.pageToSection(pagerState.currentPage)
        )
    }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect {interaction ->
            if(interaction is PressInteraction.Release){
                if(screenUiState.mode !is ChatScreenMode.Typing){
                    screenViewModel.startTyping()
                }
            }

        }
    }



    Scaffold(
        modifier= modifier
            .navigationBarsPadding(),
        topBar = {
            if(!chatScreenStateParam.globalActionsState.selectionMode){
                TopAppBarChatScreen(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
                )
            }else{
                SelectMessageBar(
                    selectMessageBarActions = selectMessageBarActionsParam,
                    modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
                )
            }
        },
        bottomBar = {


            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.BottomCenter

            ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column{
                    TextBarChat(
                        textUser = chatScreenStateParam.globalActionsState.messageUiState.messageDetails.text,
                        ontextchage = chatActionsParam.onTextChange ,
                        onDoneClicked  = chatActionsParam.onDoneClicked,
                        focusRequester = firstFocusRequester,
                        enabled = chatScreenStateParam.enabled,
                        onClickEmojiActions =  {
                            if(screenUiState.mode is ChatScreenMode.EmojiTray){
                                screenViewModel.startTyping()
                            }else{
                                screenViewModel.openEmojiTray()
                            }
                        },
                        focusStateInputBar = {
                            focusTextBarChat = it
                        },
                        hasFocus = chatScreenStateParam.hasFocus,
                        interactionSource = interactionSource

                    )

                    if(screenUiState.mode !is ChatScreenMode.EmojiTray){
                        Spacer(
                            modifier = Modifier
                                .windowInsetsPadding(WindowInsets.ime)
                                .fillMaxWidth()
                        )
                    }

                    EmojiTrayPlCl(
                        isEmojiOpen = screenUiState.mode is ChatScreenMode.EmojiTray,
                        secondFocusRequester = secondFocusRequester,
                        screenUiState = screenUiState,
                        searchAction = {
                            screenViewModel.toggleEmojiTraySearchMode()
                        },
                        focusStateInputBar = {
                            focusSearchBarMediaTray = it
                        },
                        pagerState = pagerState,
                    )

                }

            }
            }
        }
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.BottomCenter

        ) {

            Column(
                modifier = modifier
                    .fillMaxWidth(),
            ) {

                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ChatScreen(
                        modifier = modifier,
                        stateListMessages = stateListMessages,
                        chatScreenState =chatScreenStateParam,
                        chatActions = chatActionsParam,
                        selectMessageBarActions = selectMessageBarActionsParam,
                        emojiTrayState = {
                        }

                    )

                }

            }


        }
    }





}






@Composable
fun DeleteBottom(
    selectionMode: Boolean,
    onDelete: () -> Unit,
){
    AnimatedVisibility(
        visible = selectionMode,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        FloatingActionButton(
            onClick = onDelete,
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
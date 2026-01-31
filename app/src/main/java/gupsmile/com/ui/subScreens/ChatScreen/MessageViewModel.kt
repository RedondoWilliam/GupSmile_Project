package gupsmile.com.ui.subScreens.ChatScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.ui.subScreens.ChatScreen.data.Message
import gupsmile.com.ui.subScreens.ChatScreen.data.MessageRepository
import gupsmile.com.ui.subScreens.ChatScreen.data.toFormattedDateTime
import gupsmile.com.ui.subScreens.ChatScreen.data.toFormattedTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {



    val uiStateListMessages: Flow<PagingData<Message>> = Pager(
        config = PagingConfig(pageSize = 300, enablePlaceholders = false, initialLoadSize = 400),
        pagingSourceFactory = {
            messageRepository.getAllMessages()
        }
    )
        .flow
        .cachedIn(viewModelScope)


//---------------------------

    private val _stateActionOnMessagesFlow = MutableStateFlow(UiStateGlobalMessages())

    val stateActionOnMessagesFlow: StateFlow<UiStateGlobalMessages> = _stateActionOnMessagesFlow.asStateFlow()

//--------------------------

    fun updateUiState(messageDetails: MessageDetails) {

        _stateActionOnMessagesFlow.update { currentGlobalState ->
            val addDateTimeFormatToMessage = messageDetails.copy(
                timestamp = System.currentTimeMillis(),
                formatterDateTime = System.currentTimeMillis().toFormattedTime()
            )
            val updatedMessageDetails = currentGlobalState.messageUiState.copy(
                messageDetails = addDateTimeFormatToMessage,
                isEntryValid = validateInput(addDateTimeFormatToMessage)
            )
            currentGlobalState.copy(
                messageUiState = updatedMessageDetails
            )
        }
    }

    private fun validateInput(
        uiState: MessageDetails = stateActionOnMessagesFlow.value.messageUiState.messageDetails
    ): Boolean {
        return with(uiState) {
            text.isNotBlank()
        }
    }

    private fun resetMessageUiState(isEntryValid: Boolean){
        if(isEntryValid){
           _stateActionOnMessagesFlow.update { currentState ->
               currentState.copy(
                   messageUiState = MessageUiState()
               )
           }
        }
    }

    suspend fun saveMessage() {
        if (validateInput()) {
            messageRepository.insertMessage(
                stateActionOnMessagesFlow.value.messageUiState.messageDetails.toMessage()
            )
        }
        resetMessageUiState(validateInput())
    }

    fun deleteSelectedMessages(){
        viewModelScope.launch {
            messageRepository.deleteMessages(_stateActionOnMessagesFlow.value.selectedMessages.toList())
            clearSelection()
        }
    }



//-----------------------------


    fun updateListEntriesState(uiStateEntries: UiStateEntries){
        _stateActionOnMessagesFlow.update { currentState ->
            currentState.copy(
                listEntriesState = uiStateEntries
            )
        }
    }

    fun toggleMessageSelection(messageId: Int) {
        _stateActionOnMessagesFlow.update { currentGlobalState ->
            val currentSelectedIds = currentGlobalState.selectedMessages.toMutableSet()
            if (currentSelectedIds.contains(messageId)) {
                currentSelectedIds.remove(messageId)
            } else {
                currentSelectedIds.add(messageId)
            }
            currentGlobalState.copy(
                selectedMessages =  currentSelectedIds.toSet(),
                selectionMode =  currentSelectedIds.isNotEmpty()
            )
        }
    }

    fun clearSelection(){
        _stateActionOnMessagesFlow.update { currentGlobalState ->
            currentGlobalState.copy(
                selectedMessages = emptySet(),
                selectionMode = false,
                listEntriesState = UiStateEntries.DEFAULT
            )
        }
    }
}

//--------------------------

data class UiStateGlobalMessages(
    val messageUiState: MessageUiState = MessageUiState(),
    val selectionMode: Boolean = false,
    val listEntriesState: UiStateEntries = UiStateEntries.DEFAULT,
    val selectedMessages: Set<Int> = emptySet(),
)

enum class UiStateEntries{
    DEFAULT,
    ADDEDMESSAGE,
    DELETEMESSAGE,
    EDITMESSAGE
}

//-----------------------------


data class MessageUiState(
    val messageDetails: MessageDetails = MessageDetails(),
    val isEntryValid: Boolean = false
)

data class MessageDetails(
    val id: Int = 0,
    val senderId: String = "",
    val receiverId: String = "",
    var text: String = "",
    val timestamp: Long = 0,
    val formatterDateTime: String = ""
)

fun MessageDetails.toMessage(): Message = Message(
    id = id,
    senderId = senderId,
    receiverId = receiverId,
    text = text,
    timestamp = timestamp,
    formatterDateTime = formatterDateTime
)


fun Message.toMessageUiState(isEntryValid: Boolean = false): MessageUiState = MessageUiState(
    messageDetails = this.toMessageDetails(),
    isEntryValid = isEntryValid
)


fun Message.toMessageDetails(): MessageDetails = MessageDetails(
    id = id,
    senderId = senderId,
    receiverId = receiverId,
    text = text,
    timestamp = timestamp
)



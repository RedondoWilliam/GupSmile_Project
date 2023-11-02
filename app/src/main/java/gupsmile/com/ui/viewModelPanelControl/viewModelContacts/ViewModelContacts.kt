package gupsmile.com.ui.viewModelPanelControl.viewModelContacts

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.data.firebaseManager.RealTimeManager
import gupsmile.com.model.Contact
import gupsmile.com.ui.viewModelPanelControl.viewModelTranslate.StateModelTranslate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ViewModelContacts @Inject constructor(
    private val realTimeManager: RealTimeManager,
    private val authManager: AuthManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(Contact())
    val uiState: MutableStateFlow<Contact> = _uiState

    fun provideFlowListContacts(){
        
    }
}

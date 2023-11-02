package gupsmile.com.ui.viewModelPanelControl.viewModelAnalytics


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.data.firebaseManager.AnalitycsManager
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ViewModelAnalytics @Inject constructor(
    private val analyticsManager: AnalitycsManager
) : ViewModel(){


    private val _uiState = MutableStateFlow(StateAnalyticsManager())
    val uiState: MutableStateFlow<StateAnalyticsManager> = _uiState

    fun logButtonClickedCapture(newEvent: String){
        analyticsManager.logButtonClicked(buttonName = newEvent)
    }
}
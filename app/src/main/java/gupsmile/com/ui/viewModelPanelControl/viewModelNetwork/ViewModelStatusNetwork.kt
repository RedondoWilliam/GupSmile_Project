package gupsmile.com.ui.viewModelPanelControl.viewModelNetwork

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.data.repositories.WMNetworkStatusRepository
import gupsmile.com.workers.KEY_STATUS_NETWORK
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject



@HiltViewModel
class ViewModelStatusNetwork @Inject constructor(
     wmNetworkStatusRepository: WMNetworkStatusRepository
) : ViewModel() {



    val networkStatusUiState: StateFlow<NetworkStatusUiState> =
        wmNetworkStatusRepository.outputWorkInfo
        .map {info ->
            val outputResult = info.outputData.getString(KEY_STATUS_NETWORK).toBoolean()
            when{
                info.state.isFinished -> {
                   if(outputResult){
                       NetworkStatusUiState.Active
                   }else{
                       NetworkStatusUiState.Inactive
                   }
                }
                info.state == WorkInfo.State.CANCELLED -> {
                    NetworkStatusUiState.Active
                }
                else -> NetworkStatusUiState.Active
            }

        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NetworkStatusUiState.Active
        )
}


sealed interface NetworkStatusUiState {
    object Active: NetworkStatusUiState
    object Inactive: NetworkStatusUiState
}
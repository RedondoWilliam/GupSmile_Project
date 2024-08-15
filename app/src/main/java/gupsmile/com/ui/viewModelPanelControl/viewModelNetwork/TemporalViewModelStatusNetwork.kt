package gupsmile.com.ui.viewModelPanelControl.viewModelNetwork

import android.text.Editable.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.work.WorkInfo
import gupsmile.com.GupSmileApp
import gupsmile.com.data.repositories.TemporalNetworkStatusRepository
import gupsmile.com.workers.KEY_TEMPORAL_STATUS_NETWORK
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TemporalViewModelStatusNetwork(
    private val temporalNetworkStatusRepository: TemporalNetworkStatusRepository
): ViewModel() {

    val statusNetworkUiState: StateFlow<StatusNetworkUiState> =
        temporalNetworkStatusRepository.outputWorkInfo
            .map { info ->
                val outputResult = info.outputData.getString(KEY_TEMPORAL_STATUS_NETWORK)
                when{
                    info.state.isFinished ->{
                        StatusNetworkUiState.Default
                    }
                    info.state == WorkInfo.State.CANCELLED ->{
                        StatusNetworkUiState.Default
                    }
                    else  -> StatusNetworkUiState.Loading
                }

            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = StatusNetworkUiState.Default
            )

    fun verifyStatusNetwork() = temporalNetworkStatusRepository.listenableStatusNetwork()

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val temporalNetworkStatusRepository =
                    (this[APPLICATION_KEY] as GupSmileApp).container.temporalNetworkStatusRepository
                TemporalViewModelStatusNetwork(
                    temporalNetworkStatusRepository = temporalNetworkStatusRepository
                )
            }
        }
    }
}

sealed interface StatusNetworkUiState {
    object Default : StatusNetworkUiState
    object Loading : StatusNetworkUiState
    data class Complete(val output: Boolean): StatusNetworkUiState
}
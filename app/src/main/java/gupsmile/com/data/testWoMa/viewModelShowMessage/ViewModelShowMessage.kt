package gupsmile.com.data.testWoMa.viewModelShowMessage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.data.repositories.WMNetworkStatusRepository
import gupsmile.com.data.testWoMa.SHOW_MESSAGE
import gupsmile.com.data.testWoMa.TestWomaRepository
import gupsmile.com.data.testWoMa.TestWomaRepositoryIn
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateLoginWithGoogle
import gupsmile.com.ui.viewModelPanelControl.viewModelNetwork.NetworkStatusUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class ViewModelShowMessage @Inject constructor(
     private val testWomaRepository: TestWomaRepository
): ViewModel() {

    val networkUiState: StateFlow<StatusNetworkUiState> =
        testWomaRepository.outputWorkInfo
            .map {info ->
                Log.d("ShowMessage", "colector del flujo: ${ info.outputData.getString(SHOW_MESSAGE).toString()}, el id del worker es ${info.id}" )

                val output = info.outputData.getString(SHOW_MESSAGE).toString()

                when{

                    info.state.isFinished && !info.outputData.getString(SHOW_MESSAGE).isNullOrEmpty()->{
                        Log.d("showMessage", "outputresultSuccess $output")
                        Log.d("showMessage", "estado de workRequest: $info")
                        StatusNetworkUiState.Complete(outputResult =output)
                    }
                    info.state == WorkInfo.State.CANCELLED ->{
                        Log.d("showMessage", "cancelled ${info.outputData.getString(SHOW_MESSAGE)}")
                        StatusNetworkUiState.Default(output)
                    }

                    else -> {
                        Log.d("showMessage", "loading ${info.outputData.getString(SHOW_MESSAGE)}")
                        StatusNetworkUiState.Loading(output)
                    }
                }



            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = StatusNetworkUiState.noneI
            )



    suspend fun showMessage() =  testWomaRepository.showMessage()




}


 sealed interface StatusNetworkUiState{
    data class Default(val outputResult: String): StatusNetworkUiState

    data class Loading(val outputResult: String): StatusNetworkUiState

    data class Complete(val outputResult: String): StatusNetworkUiState

    data object noneI: StatusNetworkUiState



}

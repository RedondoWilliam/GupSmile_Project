package gupsmile.com.ui.viewModelPanelControl.viewModelNetwork

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Network
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class StateNetwork{
    ACTIVE,
    INACTIVE
}

data class NetworkUiState(
    var stateNetwork: StateNetwork = StateNetwork.ACTIVE
)

@Suppress("DEPRECATION")
@HiltViewModel
class ViewModelNetwork @Inject constructor( @ApplicationContext context: Context): ViewModel() {

    private val _uiState = MutableStateFlow(NetworkUiState())
    val uiState: StateFlow<NetworkUiState> = _uiState


    init {
       if(isOnline(context)){
           updateStateNetwork(StateNetwork.ACTIVE)
       }else{
           updateStateNetwork(StateNetwork.INACTIVE)
       }
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    fun updateStateNetwork(newValue: StateNetwork){
        _uiState.update {
            it.copy(
                stateNetwork = newValue
            )
        }
    }
}




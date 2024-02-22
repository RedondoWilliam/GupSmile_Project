package gupsmile.com.data.temporalConfig

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.data.firebaseManager.CloudStorageManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import javax.inject.Inject


enum class StateImagesSaved{
    LOADING,
    DONE,
    ERROR,
    UNDEFINE
}
data class TemporalImagesUrls(
    val state: StateImagesSaved = StateImagesSaved.UNDEFINE,
    var Urls: List<Any> = listOf()
)

@HiltViewModel
class ViewModelUrlsImages @Inject constructor(
    private val cloudStorageManager: CloudStorageManager
) : ViewModel() {

    val result = cloudStorageManager.getUserImagesTwo()
        .catch {
            updateStateImageSaved(newValue = StateImagesSaved.ERROR)
            Log.d("DetectedError", "UrlsImages error = ${it.message}")
        }

    private val _uiState = MutableStateFlow(TemporalImagesUrls())
    val uiState: MutableStateFlow<TemporalImagesUrls> = _uiState
    private fun updateStateImageSaved(newValue: StateImagesSaved){
        uiState.update {
            it.copy(
                state = newValue
            )
        }
    }
    fun updateUrlsImages(result: List<String>){
        uiState.update {
            it.copy(
                Urls = result
            )
        }
        if(uiState.value.Urls.isNotEmpty()){
            updateStateImageSaved(newValue = StateImagesSaved.DONE)
        }else{
            updateStateImageSaved(newValue = StateImagesSaved.LOADING)
        }
    }
}
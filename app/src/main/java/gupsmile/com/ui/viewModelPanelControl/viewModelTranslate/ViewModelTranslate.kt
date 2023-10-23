package gupsmile.com.ui.viewModelPanelControl.viewModelTranslate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.tasks.Task
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ViewModelTranslate : ViewModel() {

    private val _uiState = MutableStateFlow(StateModelTranslate())
    val uiState: MutableStateFlow<StateModelTranslate> = _uiState


    val options = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.SPANISH)
        .build()
    val englishSpanishTranslator = Translation.getClient(options)

    //    Necesitamos asugurar de que el modelo de traducción se haya descargado en el dispositivo
    var conditions = DownloadConditions.Builder()
        .requireWifi()
        .build()

    private fun translateModel(){
        viewModelScope.launch {
            englishSpanishTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener {
                    _uiState.update {
                        it.copy(
                            stateDownloadModel = StateDownloadModel.SUCCESS
                        )
                    }
                }
                .addOnFailureListener{exception ->
                    _uiState.update {
                        it.copy(
                            stateDownloadModel = StateDownloadModel.ERROR
                        )
                    }
                }

        }
    }
    private fun translateText(text: String){
        translateModel()
        if (_uiState.value.stateDownloadModel == StateDownloadModel.SUCCESS){
            viewModelScope.launch {
                val result = englishSpanishTranslator.translate(text)
                    .addOnSuccessListener { translateTextSuccess ->
                        _uiState.update {
                            it.copy(
                                stateTranslateText = StateTranslateText.SUCCESS
                            )
                        }
                    }
                    .addOnFailureListener { exception ->
                        _uiState.update {
                            it.copy(
                                stateTranslateText = StateTranslateText.ERROR,
                            )
                        }

                    }
                    .await()
                _uiState.update {
                    it.copy(
                        stringTranslated = result.toString()
                    )
                }

            }
        }
    }

    fun getTextTranslated(text: String):String{
        translateText(text = text)
        return _uiState.value.stringTranslated

    }
}


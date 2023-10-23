package gupsmile.com.ui.viewModelPanelControl.viewModelTranslate

import java.lang.Exception

data class StateModelTranslate(
    var stateDownloadModel: StateDownloadModel = StateDownloadModel.UNSPECIFY,
    var stateTranslateText: StateTranslateText = StateTranslateText.UNSPECIFY,
    var exceptionTranslateText: Exception? = null,
    var stringTranslated: String = ""
)
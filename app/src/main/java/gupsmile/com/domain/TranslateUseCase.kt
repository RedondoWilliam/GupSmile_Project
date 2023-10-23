package gupsmile.com.domain

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions


enum class ResulDownLoadModel{
    SUCCESS,
    ERROR,
    UNSPECIFY,
}


class TranslateUseCase{

    suspend  fun TranslateModel(text: String){
        var resultDownloadModel: ResulDownLoadModel = ResulDownLoadModel.UNSPECIFY


        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()
        val englishSpanishTranslator = Translation.getClient(options)

//    Necesitamos asugurar de que el modelo de traducción se haya descargado en el dispositivo
        var conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        englishSpanishTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                ResulDownLoadModel.SUCCESS
            }
            .addOnFailureListener{exception ->
                ResulDownLoadModel.ERROR
            }

        if (resultDownloadModel == ResulDownLoadModel.SUCCESS){
            englishSpanishTranslator.translate(text)
                .addOnSuccessListener { translatedText ->

                }
        }
    }


}
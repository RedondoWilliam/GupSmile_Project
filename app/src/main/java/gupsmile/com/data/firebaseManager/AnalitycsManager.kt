package gupsmile.com.data.firebaseManager

import android.content.Context
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import dagger.hilt.android.qualifiers.ApplicationContext
import gupsmile.com.data.firebaseService.FirebaseAnalitycsService

import javax.inject.Inject


class AnalitycsManager @Inject constructor(
    private val context: Context,
    private val firebaseAnalytics: FirebaseAnalytics
): FirebaseAnalitycsService
{
    override fun logEvent(eventName: String, params: Bundle){
        firebaseAnalytics.logEvent(eventName, params)

    }

    override fun  logButtonClicked(buttonName: String){
        val params = Bundle().apply{
            putString("button_name", buttonName)
        }
        firebaseAnalytics.setDefaultEventParameters(params)
        logEvent("button_clicked", params)
    }


    /**
     * nececitamos una forma de evitar el reconteo por recomposiciones de la pantalla,
     * lo que va hacer trackScreen es enviar un evento a firebase de que hemos visto una screen,
     * track screen solo se va a componer una vez*/
    @Composable
    override fun logScreenView(screenName: String){
        DisposableEffect(Unit){
            onDispose {
                val params = Bundle().apply {
                    this.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
                    this.putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenName)
                }
                firebaseAnalytics.setDefaultEventParameters(params)
                logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, params)
            }

        }
    }

    override fun logError(error: String){
        val params = Bundle().apply {
            putString("error", error)
        }
        logEvent("error", params)
    }
}
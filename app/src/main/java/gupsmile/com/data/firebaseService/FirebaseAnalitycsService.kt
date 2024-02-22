package gupsmile.com.data.firebaseService

import android.os.Bundle
import androidx.compose.runtime.Composable

interface FirebaseAnalitycsService{
    fun logEvent(eventName: String, params: Bundle)
    fun  logButtonClicked(buttonName: String)
    @Composable
    fun logScreenView(screenName: String)
    fun logError(error: String)
}
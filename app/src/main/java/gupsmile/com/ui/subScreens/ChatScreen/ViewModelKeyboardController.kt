package gupsmile.com.ui.subScreens.ChatScreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ViewModelKeyboardController @Inject constructor(): ViewModel(){

     var stateKeyboardController: Boolean = false

    fun updateStateKeyboardController(state: Boolean){
        stateKeyboardController = state
    }


}
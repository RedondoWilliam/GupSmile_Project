package gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import gupsmile.com.data.MyModule
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.model.AuthRes
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import kotlinx.coroutines.async
//import gupsmile.com.firebaseManager.AuthRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModelAuthentication @Inject constructor(
    private val auth: AuthManager
    ): ViewModel() {

    private val _uiState = MutableStateFlow(StateAuthenticationManager())
    val uiState: StateFlow<StateAuthenticationManager> = _uiState



    /**
     * Register NewUser Control elements ->
     * */


    fun updateStateRegisterNewUser(newValue: RegisterNewUser){
        _uiState.update {
            it.copy(
                stateRegisterNewUser = newValue
            )
        }
    }

    fun updateStateEqualsPasswordRegisterNewUser(newValue: StateEqualsPasswordRegisterNewUser){
        _uiState.update {
            it.copy(
                equalsPasswordsRegisterNewUser = newValue
            )
        }
    }


    fun updateStateCurrentUser(newValue: StateCurrentUser){
        _uiState.update {
            it.copy(
                stateCurrentUser =newValue
            )
        }
    }

    fun updateEmailRegisterNewUser(newValue: String){
        _uiState.update {
            it.copy(
                emailRegisterNewUser = newValue
            )
        }
    }

    fun updatePasswordRegisterNewUser(newValue: String){
        _uiState.update {
            it.copy(
                passwordRegisterNewUser = newValue
            )
        }
    }

    fun updateConfirmPasswordRegisterNewUser(newValue: String){
        _uiState.update {
            it.copy(
                confirmPasswordRegisterNewUser = newValue
            )
        }
    }

    fun updateStateAuthentication(newValue: StateAuthentication){
        _uiState.update {
            it.copy(
                stateAuthentication = newValue
            )
        }
    }

    fun resetElementsRegisterNewUser(){
        _uiState.update {
            it.copy(
                emailRegisterNewUser = "",
                passwordRegisterNewUser = "",
                confirmPasswordRegisterNewUser = "",
                stateAuthentication = StateAuthentication.UNSPECIFY,
                stateRegisterNewUser = RegisterNewUser.UNSPECIFY,
                typeErrorRegisterNewUser = ""
            )
        }
    }


    private fun registerNewUser(){
        updateStateRegisterNewUser(newValue = RegisterNewUser.LOADING)
        viewModelScope.launch{
            if(_uiState.value.emailRegisterNewUser.isNotEmpty()
                && _uiState.value.passwordRegisterNewUser.isNotEmpty()
                ){
                when(
                    val result = auth.createUserWithEmailAndPassword(
                        email = _uiState.value.emailRegisterNewUser,
                        password = _uiState.value.passwordRegisterNewUser )

                ){
                    is AuthRes.Succes -> {
                        updateStateRegisterNewUser(RegisterNewUser.SUCCESS)
                        if(auth.getCurrentUser() != null){
                            auth.signOut()
                        }
                    }
                    is AuthRes.Error -> {
                        _uiState.update {
                            it.copy(
                                stateRegisterNewUser = RegisterNewUser.ERROR,
                                typeErrorRegisterNewUser = result.errorMessage
                            )

                        }
                    }
                }
            }else{
                _uiState.update {
                    it.copy(
                        stateRegisterNewUser = RegisterNewUser.SPACEEMPTY
                    )
                }
            }
        }

    }

    fun validatePasswordsAndRegisterNewUser(){
        if(_uiState.value.passwordRegisterNewUser == _uiState.value.confirmPasswordRegisterNewUser){
            updateStateEqualsPasswordRegisterNewUser(
                newValue = StateEqualsPasswordRegisterNewUser.EQUALS
            )
            registerNewUser()
        }else{
            updateStateEqualsPasswordRegisterNewUser(
                newValue = StateEqualsPasswordRegisterNewUser.UNEQUALS
            )
        }
    }


    /**
     * SignInUser Control elements ->
     * */
    fun updateSignInUserEmail(newValue: String){
        _uiState.update {
            it.copy(
                signInUserEmail = newValue
            )
        }
    }

    fun updateSignInUserPassword(newValue: String){
        _uiState.update {
            it.copy(
               signInUserPassword = newValue
            )
        }
    }


    fun updateConfirmSignInUserPassword(newValue: String){
        _uiState.update {
            it.copy(
               confirmSignInUserPassword = newValue
            )
        }
    }

    fun updateStateSignInUser(newValue: StateSignInUser){
        _uiState.update {
            it.copy(
                stateSignInUser = newValue
            )
        }
    }

    fun updateTypeErrorSignInUser(newValue: String){
        _uiState.update {
            it.copy(
                typeErrorSignInUser = newValue
            )
        }
    }

    fun resetElementsSignInUser(){
        _uiState.update {
            it.copy(
                signInUserEmail = "",
                signInUserPassword = "",
                confirmSignInUserPassword =  "",
                stateSignInUser = StateSignInUser.UNSPECIFY,
                typeErrorSignInUser = ""
            )
        }
    }

    fun signInUser(){
        updateStateSignInUser(newValue = StateSignInUser.LOADING)
        viewModelScope.launch{
            if(_uiState.value.signInUserEmail.isNotEmpty()
                && _uiState.value.signInUserPassword.isNotEmpty()
            ){
                when(
                    val result = auth.signInWithEmailAndPassword(
                        email = _uiState.value.signInUserEmail,
                        password = _uiState.value.signInUserPassword)

                ){
                    is AuthRes.Succes -> {

                        _uiState.update {
                            it.copy(
                                stateSignInUser = StateSignInUser.SUCCESS
                            )
                        }
                    }
                    is AuthRes.Error -> {
                        _uiState.update {
                            it.copy(
                                stateSignInUser = StateSignInUser.ERROR,
                                typeErrorSignInUser = result.errorMessage
                            )

                        }
                    }
                }
            }else {
                _uiState.update {
                    it.copy(
                       stateSignInUser = StateSignInUser.SPACEEMPTY
                    )
                }
            }
        }
    }



    /**
     * retrieve password Elements
     * */
    fun updateStateRetrievePassword(newValue: StateRetrievePassoword){
        _uiState.update {
            it.copy(
                stateRetrievePassoword = newValue
            )
        }
    }

    fun updateErrorRetrievePassword(newValue: String){
        _uiState.update {
            it.copy(
                errorRetrievePassword = newValue
            )
        }
    }

    fun updateEmailRetrievePassword(newValue: String){
        _uiState.update {
            it.copy(
                emailRetrievePassword = newValue
            )
        }
    }

    fun resetElementsRetrievePassword(){
        _uiState.update {
            it.copy(
                stateRetrievePassoword = StateRetrievePassoword.UNSPECIFY,
                errorRetrievePassword = "",
                emailRetrievePassword = ""
            )
        }
    }

    fun retrievePassword(){
        updateStateRetrievePassword(newValue = StateRetrievePassoword.LOADING)
        viewModelScope.launch{
            if(_uiState.value.emailRetrievePassword.isNotEmpty()
            ){
                when(
                    val result = auth.resetPassword(email = _uiState.value.emailRetrievePassword)
                ) {
                    is AuthRes.Succes -> {

                        _uiState.update {
                            it.copy(
                               stateRetrievePassoword = StateRetrievePassoword.SUCCESS
                            )
                        }
                    }
                    is AuthRes.Error -> {
                        _uiState.update {
                            it.copy(
                                stateRetrievePassoword = StateRetrievePassoword.ERROR,
                                errorRetrievePassword = result.errorMessage
                            )

                        }
                    }
                }
            }else {
                _uiState.update {
                    it.copy(
                        stateRetrievePassoword = StateRetrievePassoword.SPACEEMPTY
                    )
                }
            }
        }
    }




    /**
     * Login as visitor elements
     * */


    fun updateStateLoginAsVisitor(newValue: StateLoginAsVisitor){
        _uiState.update {
            it.copy(
                stateLoginAsVisitor = newValue
            )
        }

    }
    fun resetStateLoginAsVisitor(){
        _uiState.update {
            it.copy(
                stateLoginAsVisitor = StateLoginAsVisitor.UNSPECIFIED,
                errorLoginAsVisitor =  ""
            )
        }
    }

    fun loginAsVisitor(){
        updateStateLoginAsVisitor(newValue = StateLoginAsVisitor.LOADING)
        viewModelScope.launch {
            if (auth != null) {
                when(val result = auth.signInAnonymously()){
                    is AuthRes.Succes -> {
                        updateStateLoginAsVisitor(newValue = StateLoginAsVisitor.SUCCESS)
                    }

                    is AuthRes.Error -> {
                        updateStateLoginAsVisitor(newValue = StateLoginAsVisitor.ERROR)
                        _uiState.update {
                            it.copy(
                                errorLoginAsVisitor = result.errorMessage
                            )
                        }
                    }
                }
            }
        }
    }


    /**
     * general accounts elements
     * */
    fun getCurrentUser() { auth.getCurrentUser()}

    fun signOutCurrentUser() {auth.signOut()}


    init {
        if(auth.getCurrentUser() != null){
            updateStateCurrentUser(newValue = StateCurrentUser.ACTIVE)
        }else{
            updateStateCurrentUser(newValue = StateCurrentUser.NULE)
        }
    }


}


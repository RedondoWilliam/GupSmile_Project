package gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication

import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateAuthentication.*

data class StateAuthenticationManager(

    /**
     * RegisterNewUser Elements
     * */
    var emailRegisterNewUser: String = "",
    var passwordRegisterNewUser: String = "",
    var confirmPasswordRegisterNewUser: String = "",
    var stateAuthentication: StateAuthentication = UNSPECIFY,
    var stateRegisterNewUser: RegisterNewUser = RegisterNewUser.UNSPECIFY,
    var typeErrorRegisterNewUser: String = "",
    var equalsPasswordsRegisterNewUser: StateEqualsPasswordRegisterNewUser = StateEqualsPasswordRegisterNewUser.UNDIFINE,


    /**
     * SignInUser  Elements
     * */
    var signInUserEmail: String = "",
    var signInUserPassword: String = "",
    var confirmSignInUserPassword: String = "",
    var stateSignInUser: StateSignInUser = StateSignInUser.UNSPECIFY,
    var typeErrorSignInUser: String = "",


    /**
     * retrieve password Elements
     * */
    var stateRetrievePassoword: StateRetrievePassoword = StateRetrievePassoword.UNSPECIFY,
    var errorRetrievePassword: String = "",
    var emailRetrievePassword: String = "",

    var proofViewModelus: String = "esto es una prueba",

    var stateCurrentUser: StateCurrentUser = StateCurrentUser.NULE,

    /**
     * Login as visitor elements
     * */

    var stateLoginAsVisitor: StateLoginAsVisitor = StateLoginAsVisitor.UNSPECIFIED,
    var errorLoginAsVisitor: String = "",


    /**
     * general state active users
     * */
    var stateLoginWithGoogle: StateLoginWithGoogle = StateLoginWithGoogle.UNSPECIFIED

)

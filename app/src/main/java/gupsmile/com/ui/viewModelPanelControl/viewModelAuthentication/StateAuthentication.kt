package gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication

enum class StateAuthentication{
    SUCCESS,
    ERROR,
    UNSPECIFY
}

enum class RegisterNewUser{
    SUCCESS,
    ERROR,
    SPACEEMPTY,
    UNSPECIFY,
    LOADING
}

enum class StateEqualsPasswordRegisterNewUser{
    EQUALS,
    UNEQUALS,
    UNDIFINE
}


enum class StateSignInUser{
    SUCCESS,
    ERROR,
    UNSPECIFY,
    SPACEEMPTY,
    LOADING
}

enum class StateRetrievePassoword{
    SUCCESS,
    ERROR,
    UNSPECIFY,
    SPACEEMPTY,
    LOADING
}


enum class StateCurrentUser{
    NULE,
    ACTIVE,
    LOGOUTCURRENTUSER,
    LOGINCURRENTUSER,
}

enum class StateLoginAsVisitor{
    SUCCESS,
    ERROR,
    UNSPECIFIED,
    LOADING
}

enum class StateLoginWithGoogle{
    ACTIVE,
    SUCCESS,
    ERROR,
    UNSPECIFIED,
    LOADING
}




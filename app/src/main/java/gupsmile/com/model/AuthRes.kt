package gupsmile.com.model

sealed class AuthRes<out T>{
    data class Succes<T>(val data: T): AuthRes<T>()
    data class Error(val errorMessage: String):AuthRes<Nothing>()
}

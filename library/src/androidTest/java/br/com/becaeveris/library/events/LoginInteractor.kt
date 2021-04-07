package br.com.becaeveris.library.events

sealed class LoginInteractor {
    object GetSession: LoginInteractor()
    data class Authenticate(val password: String): LoginInteractor()
}
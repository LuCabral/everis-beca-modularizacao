package br.com.becaeveris.library.states

sealed class LoginStates {
    data class GetSessionResult(val keyboard: ArrayList<br.com.becaeveris.library.model.KeyboardItem>): LoginStates()
    data class GetSessionError(val exception: Exception): LoginStates()

    data class AuthenticateResult(val result: Boolean): LoginStates()
    data class AuthenticateError(val exception: Exception): LoginStates()
}
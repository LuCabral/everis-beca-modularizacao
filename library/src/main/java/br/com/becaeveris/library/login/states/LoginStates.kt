package br.com.becaeveris.library.login.states

import br.com.becaeveris.library.login.model.KeyboardItem

sealed class LoginStates {
    data class GetSessionResult(val keyboard: ArrayList<KeyboardItem>): LoginStates()
    data class GetSessionError(val exception: Exception): LoginStates()

    data class AuthenticateResult(val result: Boolean): LoginStates()
    data class AuthenticateError(val exception: Exception): LoginStates()
}
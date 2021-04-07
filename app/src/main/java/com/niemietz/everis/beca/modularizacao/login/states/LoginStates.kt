package com.niemietz.everis.beca.modularizacao.login.states

import br.com.becaeveris.library.model.KeyboardItem

sealed class LoginStates {
    data class GetSessionResult(val keyboard: ArrayList<br.com.becaeveris.library.model.KeyboardItem>): LoginStates()
    data class GetSessionError(val exception: Exception): LoginStates()

    data class AuthenticateResult(val result: Boolean): LoginStates()
    data class AuthenticateError(val exception: Exception): LoginStates()
}
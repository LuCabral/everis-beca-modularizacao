package com.niemietz.everis.beca.modularizacao.login.repository

import br.com.becaeveris.library.interfaces.LoginAPI
import br.com.becaeveris.library.model.AuthenticateRequest
import br.com.becaeveris.library.model.GETSessionRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(private val api: br.com.becaeveris.library.interfaces.LoginAPI) {
    suspend fun getSession(request: br.com.becaeveris.library.model.GETSessionRequest) =
        withContext(Dispatchers.IO) {
            api.getSession(request)
        }

    suspend fun authenticate(request: br.com.becaeveris.library.model.AuthenticateRequest) =
        withContext(Dispatchers.IO) {
            api.authenticate(request)
        }
}
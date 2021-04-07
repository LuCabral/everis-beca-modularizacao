package br.com.becaeveris.library.login.repository

import br.com.becaeveris.library.login.interfaces.LoginAPI
import br.com.becaeveris.library.login.model.AuthenticateRequest
import br.com.becaeveris.library.login.model.GETSessionRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(private val api: LoginAPI) {
    suspend fun getSession(request: GETSessionRequest) =
        withContext(Dispatchers.IO) {
            api.getSession(request)
        }

    suspend fun authenticate(request: AuthenticateRequest) =
        withContext(Dispatchers.IO) {
            api.authenticate(request)
        }
}
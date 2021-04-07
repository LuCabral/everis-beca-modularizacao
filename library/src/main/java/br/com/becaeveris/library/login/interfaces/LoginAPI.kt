package br.com.becaeveris.library.login.interfaces

import br.com.becaeveris.library.login.constants.APIConstants.AUTHENTICATE
import br.com.becaeveris.library.login.constants.APIConstants.GET_SESSION
import br.com.becaeveris.library.login.model.GETSessionResponse
import br.com.becaeveris.library.login.model.GETSessionRequest
import br.com.becaeveris.library.login.model.AuthenticateResponse
import br.com.becaeveris.library.login.model.AuthenticateRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    @POST(GET_SESSION)
    suspend fun getSession(
        @Body request: GETSessionRequest
    ): GETSessionResponse

    @POST(AUTHENTICATE)
    suspend fun authenticate(
        @Body request: AuthenticateRequest
    ): AuthenticateResponse
}
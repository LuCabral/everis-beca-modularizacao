package com.niemietz.everis.beca.modularizacao.login.interfaces

import br.com.becaeveris.library.constants.APIConstants.AUTHENTICATE
import br.com.becaeveris.library.constants.APIConstants.GET_SESSION
import br.com.becaeveris.library.model.GETSessionResponse
import br.com.becaeveris.library.model.GETSessionRequest
import br.com.becaeveris.library.model.AuthenticateResponse
import br.com.becaeveris.library.model.AuthenticateRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    @POST(GET_SESSION)
    suspend fun getSession(
        @Body request: br.com.becaeveris.library.model.GETSessionRequest
    ): br.com.becaeveris.library.model.GETSessionResponse

    @POST(AUTHENTICATE)
    suspend fun authenticate(
        @Body request: br.com.becaeveris.library.model.AuthenticateRequest
    ): br.com.becaeveris.library.model.AuthenticateResponse
}
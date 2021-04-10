package com.niemietz.everis.beca.modularizacao

import android.app.Application
import com.niemietz.everis.beca.core.BackendClient
import com.niemietz.everis.beca.core.BuildConfig

class CustomApplication: Application() {

    init {
        BackendClient.setURL(BuildConfig.BECA_API_HOST)
        BackendClient.setTimeout(99999)
    }
}
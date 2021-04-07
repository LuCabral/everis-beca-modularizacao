package br.com.becaeveris.library.login.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthenticateRequest (
    @SerializedName("sessionId")
    val sessionId: String,
    @SerializedName("password")
    val password: String
): Parcelable
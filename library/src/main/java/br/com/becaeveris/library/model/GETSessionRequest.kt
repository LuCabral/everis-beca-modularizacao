package br.com.becaeveris.library.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GETSessionRequest (
    @SerializedName("deviceId")
    val deviceId: String
): Parcelable
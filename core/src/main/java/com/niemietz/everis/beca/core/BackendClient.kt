package br.com.becaeveris.library.constants

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object BackendClient {
    private lateinit var retrofit: Retrofit

    private var timeout: Long = 60
    private var URL = ""

    fun setTimeout(value: Long) {
        timeout = value
    }

    fun setURL(value: String): Boolean {
        var result = false

        if (URL.isEmpty()) {
            val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .addNetworkInterceptor {
                    val original: Request = it.request()
                    val request: Request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build()

                    it.proceed(request)
                }
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(value)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            URL = value

            result = true
        }

        return result
    }

    fun <T> api(_class: Class<T>): T {
        if (!BackendClient::retrofit.isInitialized) {
            throw Exception("Retrofit was not initialized")
        }
        if (URL.isEmpty()) {
            throw Exception("API URL is not defined")
        }
        return retrofit.create(_class)
    }
}
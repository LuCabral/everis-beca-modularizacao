package br.com.becaeveris.library.viewmodel

import android.content.Context
import android.provider.Settings.Secure
import android.se.omapi.Session
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.becaeveris.library.events.LoginEvents
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
    private val context: Context,
    private var repository: br.com.becaeveris.library.repository.LoginRepository,
    private val checkInternetConnection: Boolean = true
): ViewModel() {
    private val job: Job = Job()
    private val currentJob: CoroutineContext =
        Dispatchers.Main + job
    val events = MutableLiveData<br.com.becaeveris.library.events.LoginEvents>()
    val states = MutableLiveData<br.com.becaeveris.library.states.LoginStates>()

    fun interact(interaction: br.com.becaeveris.library.events.LoginInteractor) {
        when (interaction) {
            is br.com.becaeveris.library.events.LoginInteractor.GetSession -> getSession()
            is br.com.becaeveris.library.events.LoginInteractor.Authenticate -> authenticate(interaction.password)
        }
    }

    private fun getSession() {
        if (isConnected2Internet(context)) {
            events.value = br.com.becaeveris.library.events.LoginEvents.StartLoading

            CoroutineScope(currentJob).launch {
                try {
                    val deviceId = Secure.getString(
                        context.contentResolver,
                        Secure.ANDROID_ID
                    )

                    val response = repository.getSession(
                        br.com.becaeveris.library.model.GETSessionRequest(
                            deviceId
                        )
                    )

                    setCoreSession(response.sessionId)

                    states.value = br.com.becaeveris.library.states.LoginStates.GetSessionResult(
                        response.keyboard
                    )
                } catch (ex: Exception) {
                    states.value = br.com.becaeveris.library.states.LoginStates.GetSessionError(ex)
                }
            }
        } else {
            events.value = br.com.becaeveris.library.events.LoginEvents.NoInternet
        }
    }

    private fun isConnected2Internet(context: Context): Boolean {

    }

    private fun authenticate(password: String) {
        if (isConnected2Internet(context)) {
            events.value = LoginEvents.StartLoading

            CoroutineScope(currentJob).launch {
                try {
                    Session.id?.let {
                        val sessionId = it

                        val response = repository.authenticate(
                            br.com.becaeveris.library.model.AuthenticateRequest(
                                sessionId,
                                password
                            )
                        )

                        setCoreUserContent(response.content)

                        states.value = br.com.becaeveris.library.states.LoginStates.AuthenticateResult(
                            response.result
                        )
                    } ?: run {
                        events.value = br.com.becaeveris.library.events.LoginEvents.NoSession
                    }
                } catch (ex: Exception) {
                    states.value = br.com.becaeveris.library.states.LoginStates.GetSessionError(ex)
                }
            }
        } else {
            events.value = br.com.becaeveris.library.events.LoginEvents.NoInternet
        }
    }

    private fun setCoreSession(sessionId: String) {
        Session.id = sessionId
    }

    private fun setCoreUserContent(userContent: br.com.becaeveris.library.model.AuthenticateResponseContent) {
        Session.content = JSONObject(Gson().toJson(userContent))
    }
}
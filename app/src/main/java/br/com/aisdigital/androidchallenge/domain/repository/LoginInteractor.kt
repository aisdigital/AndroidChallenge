package br.com.aisdigital.androidchallenge.domain.repository

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import br.com.aisdigital.androidchallenge.domain.model.RequestState
import br.com.aisdigital.androidchallenge.domain.model.error.ErrorHandler
import br.com.aisdigital.androidchallenge.domain.model.login.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginInteractor(
    private val repository: Repository,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO),
    private val resources: Resources,
    private val errorHandler: ErrorHandler = ErrorHandler(resources)
) {
    val requestState = MutableLiveData<RequestState<LoginResponse>>(RequestState.Idle)

    fun doLogin(token: String) {
        requestState.postValue(RequestState.Loading)

        coroutineScope.launch {
            try {
                val response = repository.doLogin(token)

                requestState.postValue(RequestState.Success(response))
            } catch (e: Exception) {
                requestState.postValue(
                    RequestState.Error(
                        errorHandler.buildErrorResponse(e)
                    )
                )
            }
        }
    }

}
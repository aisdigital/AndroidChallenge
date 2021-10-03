package br.com.aisdigital.androidchallenge.domain.repository.interactor

import androidx.lifecycle.MutableLiveData
import br.com.aisdigital.androidchallenge.domain.model.RequestState
import br.com.aisdigital.androidchallenge.domain.model.auth.AuthResponse
import br.com.aisdigital.androidchallenge.domain.model.error.ErrorHandler
import br.com.aisdigital.androidchallenge.domain.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class AuthInteractor(
    private val repository: Repository,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO),
    private val errorHandler: ErrorHandler
) {
    val requestState = MutableLiveData<RequestState<AuthResponse>>(RequestState.Idle)

    fun doAuth(email: String, password: String) {
        requestState.postValue(RequestState.Loading)

        coroutineScope.launch {
            try {
                val response = repository.doAuthenticate(email, password)

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
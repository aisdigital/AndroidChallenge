package br.com.aisdigital.androidchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

data class LoadingState(
    val isLoading: Boolean = true
)

data class ValidationErrorState(
    val emailValidationError: String,
    val passwordValidationError: String
)

data class ResultState(
    val success: Boolean
)

class LoginViewModel : ViewModel() {
    private val loadingState = MutableLiveData<LoadingState>()
    val loadingStateLiveData : LiveData<LoadingState> get() = loadingState

    private val validationErrorState = MutableLiveData<ValidationErrorState>()
    val validationErrorLiveData : LiveData<ValidationErrorState> get() = validationErrorState

    private val resultState = MutableLiveData<ResultState>()
    val resultStateLiveData : LiveData<ResultState> get() = resultState
}
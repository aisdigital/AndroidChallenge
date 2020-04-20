package br.com.aisdigital.androidchallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.core.domain.model.Result
import br.com.aisdigital.androidchallenge.core.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _viewState = MutableLiveData<LoginViewState>()
    val viewState: LiveData<LoginViewState>
        get() = _viewState

    fun onLoginClick(email: String, password: String) {
        if (email.isBlank()) {
            _viewState.value = LoginViewState.ShowEmailRequiredError
            return
        }
        if (!email.isEmail()) {
            _viewState.value = LoginViewState.ShowInvalidEmailError
            return
        }
        if (password.isBlank()) {
            _viewState.value = LoginViewState.ShowPasswordRequiredError
            return
        }

        viewModelScope.launch {
            val result = loginUseCase(email, password)
            if (result is Result.Success) {
                _viewState.value = LoginViewState.OnSuccess
            } else {
                _viewState.value = LoginViewState.OnFailure
            }
        }
    }
}
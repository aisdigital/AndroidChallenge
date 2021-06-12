package br.com.aisdigital.androidchallenge.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.network.Resource
import br.com.aisdigital.androidchallenge.repository.AuthRepository
import br.com.aisdigital.androidchallenge.responses.AuthResponse
import br.com.aisdigital.androidchallenge.responses.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _authResponse: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    val authResponse: LiveData<Resource<AuthResponse>>
        get() = _authResponse

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun auth(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _authResponse.value = repository.auth(email, password)
    }

    fun login(
        token: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(token)
    }

}
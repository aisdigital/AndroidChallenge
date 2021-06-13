package br.com.aisdigital.androidchallenge.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.data.network.Resource
import br.com.aisdigital.androidchallenge.data.repository.AuthRepository
import br.com.aisdigital.androidchallenge.data.responses.AuthResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _authResponse: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    val authResponse: LiveData<Resource<AuthResponse>>
        get() = _authResponse

    fun auth(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _authResponse.value = repository.auth(email, password)
    }

    fun saveAuthToken(token: String) = viewModelScope.launch {
        repository.saveAuthToken(token)
    }

}
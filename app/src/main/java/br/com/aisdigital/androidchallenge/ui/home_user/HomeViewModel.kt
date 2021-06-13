package br.com.aisdigital.androidchallenge.ui.home_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.data.network.Resource
import br.com.aisdigital.androidchallenge.data.repository.HomeRepository
import br.com.aisdigital.androidchallenge.data.responses.LoginResponse
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {
    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        token: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(token)
    }
}
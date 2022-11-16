package br.com.aisdigital.androidchallenge.viewmodel

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.repository.MainRepository

class MainViewModel : ViewModel() {

    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val isEmailValid: MutableLiveData<Boolean> = MutableLiveData()
    val isPasswordEmpty: MutableLiveData<Boolean> = MutableLiveData()

    private val repository = MainRepository()
    private val token: MutableLiveData<String> = MutableLiveData()

    fun verifyLoginInfo() {
        isEmailValid.value = Patterns.EMAIL_ADDRESS.matcher(email.value!!).matches()
        isPasswordEmpty.value = password.value?.isEmpty()

        if (isEmailValid.value!! && isPasswordEmpty.value?.not()!!) {
            login()
        }
    }

    fun getToken() {
        repository.getToken()
    }

    fun saveToken(): MutableLiveData<String> {
        token.value = repository.token.value
        return token
    }

    private fun login() {
        repository.login(token.value)
    }

    fun isSuccessfulLogin(): MutableLiveData<Boolean> {
        return repository.loginSuccessful
    }
}
package br.com.aisdigital.androidchallenge.presentation

import br.com.aisdigital.androidchallenge.model.User

sealed class LoginState {
    object Loading : LoginState()
    data class Error(val errorMessageId: Int) : LoginState()
    data class Success(val user: User) : LoginState()
}
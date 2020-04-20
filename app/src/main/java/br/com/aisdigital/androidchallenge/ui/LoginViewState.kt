package br.com.aisdigital.androidchallenge.ui

sealed class LoginViewState {

    object ShowEmailRequiredError : LoginViewState()

    object ShowInvalidEmailError : LoginViewState()

    object ShowPasswordRequiredError : LoginViewState()

    object OnSuccess : LoginViewState()

    object OnFailure : LoginViewState()
}
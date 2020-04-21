package br.com.aisdigital.androidchallenge.domain.login

import java.util.regex.Pattern

class LoginValidator {

    fun isValid(login: Login): Boolean {
        val emailRegexValidation = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
        val pattern = Pattern.compile(emailRegexValidation)

        return pattern.matcher(login.email).matches() && login.password.isNotEmpty()
    }

    fun isAuthenticated(login: Login): Boolean {

        return isValid(login) && login.token.isNotEmpty()
    }
}
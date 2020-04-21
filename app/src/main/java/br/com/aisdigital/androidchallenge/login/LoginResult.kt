package br.com.aisdigital.androidchallenge.login

import br.com.aisdigital.androidchallenge.login.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)

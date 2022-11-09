package br.com.aisdigital.androidchallenge.domain

import android.util.Patterns

class ValidateEmailUsecase {

    fun isValidEmail(email: String?) : Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
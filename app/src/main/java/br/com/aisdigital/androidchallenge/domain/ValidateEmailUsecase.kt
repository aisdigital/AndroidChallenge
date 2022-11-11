package br.com.aisdigital.androidchallenge.domain

import androidx.core.util.PatternsCompat

class ValidateEmailUsecase {

    fun isValidEmail(email: String?) : Boolean {
        return !email.isNullOrEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
}
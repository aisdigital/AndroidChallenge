package br.com.aisdigital.androidchallenge.domain.utils

import androidx.core.util.PatternsCompat

fun String?.isEmailValid() = !isNullOrEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun String?.isPasswordValid() = !isNullOrEmpty()
package br.com.aisdigital.androidchallenge.domain.utils

import android.util.Patterns

fun String?.isEmailValid() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String?.isPasswordValid() = !isNullOrEmpty()
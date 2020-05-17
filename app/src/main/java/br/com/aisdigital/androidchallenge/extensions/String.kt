package br.com.aisdigital.androidchallenge.extensions

import android.util.Patterns
import java.lang.Exception
import java.util.regex.Pattern

fun String?.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this ?: "").matches()

fun String?.isValidPassword(): Boolean {
    if (this == null || this.length < 3) return false

    val str = this
    var valid = true

    // A senha deve conter pelo menos um número ou letra minúscula
    var exp = ".*[a-z0-9].*"
    var pattern = Pattern.compile(exp)
    var matcher = pattern.matcher(str)
    if (!matcher.matches()) {
        valid = false
    }

    // A senha deve conter pelo menos uma letra maiúscula
    exp = ".*[A-Z].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(str)
    if (!matcher.matches()) {
        valid = false
    }

    // A senha deve conter pelo menos um caractere especial
    // Caracteres especiais permitidos : "~!@#$%^&*()-_=+|/,."';:{}[]<>?"
    exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(str)
    if (!matcher.matches()) {
        valid = false
    }

    return valid
}

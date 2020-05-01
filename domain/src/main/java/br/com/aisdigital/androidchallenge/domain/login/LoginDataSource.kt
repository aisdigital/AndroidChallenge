package br.com.aisdigital.androidchallenge.domain.login

import br.com.aisdigital.androidchallenge.domain.user.User

interface LoginDataSource {

    fun isUserLoggedIn(): Boolean

    fun getUser(): User?

    fun save(user: User)

    fun clear()
}
package br.com.aisdigital.androidchallenge.core.data.interfaces

import br.com.aisdigital.androidchallenge.core.domain.model.User

interface UserCache {

    fun add(user: User)

    fun getUser() : User?

    fun isLoggedIn() : Boolean
}


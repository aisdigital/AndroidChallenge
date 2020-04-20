package br.com.aisdigital.androidchallenge.framework

import br.com.aisdigital.androidchallenge.core.data.interfaces.UserCache
import br.com.aisdigital.androidchallenge.core.domain.model.User

/**
 *  We can easily change this implementation with database persistence.
 *  Just create another class that implements UserCache, inject into repository and we'll be ready to go.
 */
class UserInMemoryCache :
    UserCache {

    private var loggedUser: User? = null

    override fun add(user: User) {
        loggedUser = user
    }

    override fun getUser() = loggedUser

    override fun isLoggedIn() = loggedUser != null
}
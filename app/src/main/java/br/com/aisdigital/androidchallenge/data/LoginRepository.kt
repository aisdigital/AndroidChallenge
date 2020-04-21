package br.com.aisdigital.androidchallenge.data

import br.com.aisdigital.androidchallenge.data.model.User
import io.reactivex.Observable


class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }


    fun auth(username: String, password: String): Observable<String> {
        return dataSource.auth(username, password).map {
            it
        }
    }

    fun getUSer(username: String, password: String): Observable<User> {
        return dataSource.getUser().map {
            setLoggedInUser(it)
            it
        }
    }

    private fun setLoggedInUser(loggedInUser: User) {
        this.user = loggedInUser
    }
}

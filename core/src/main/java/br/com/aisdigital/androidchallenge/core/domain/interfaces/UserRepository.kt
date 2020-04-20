package br.com.aisdigital.androidchallenge.core.domain.interfaces

import br.com.aisdigital.androidchallenge.core.domain.model.Result
import br.com.aisdigital.androidchallenge.core.domain.model.User

interface UserRepository {

    /**
     *  Authenticate user.
     *  @param email User email.
     *  @param password User password.
     *  @return True if login has succeed.
     */
    suspend fun login(email: String, password: String): Result<Boolean>

    /**
     *  Get logged user .
     *  @return User profile.
     */
    suspend fun getLoggedUser(): User?

    /**
     * Check if user is logged in.
     * @return True if it is.
     */
    suspend fun isLoggedIn(): Boolean
}
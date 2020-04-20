package br.com.aisdigital.androidchallenge.core.data.repository

import br.com.aisdigital.androidchallenge.core.data.interfaces.UserCache
import br.com.aisdigital.androidchallenge.core.data.interfaces.UserRemote
import br.com.aisdigital.androidchallenge.core.domain.interfaces.UserRepository
import br.com.aisdigital.androidchallenge.core.domain.model.Result

class UserDefaultRepository(private val cache: UserCache, private val remote: UserRemote) :
    UserRepository {

    override suspend fun login(email: String, password: String): Result<Boolean> {
        return try {
            val authorization = remote.getAuthToken(email, password)
            cache.add(remote.login(authorization.token))
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getLoggedUser() = cache.getUser()

    override suspend fun isLoggedIn() = cache.isLoggedIn()
}
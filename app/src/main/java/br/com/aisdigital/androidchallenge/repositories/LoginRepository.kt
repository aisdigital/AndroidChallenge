package br.com.aisdigital.androidchallenge.repositories

import br.com.aisdigital.androidchallenge.data.local.UserDao
import br.com.aisdigital.androidchallenge.data.local.asEntitieUser
import br.com.aisdigital.androidchallenge.data.remote.*
import br.com.aisdigital.androidchallenge.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val dao: UserDao,
    private val api: AndroidChallengeApi
) {
    fun login(email: String, password: String) {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            val tokenRequest = api.getToken(email, password)
            if (tokenRequest.isSuccessful) {
                val loginRequest = api.login(tokenRequest.body()!!.token)
                if (loginRequest.isSuccessful) {
                    val user = loginRequest.body()!!.asDatabaseUser()
                    user.email = email
                    user.token = tokenRequest.body()!!.token
                    dao.save(user)
                }
            }
        }
    }

    fun getLoggedUser(): Flow<User?> {
        return dao.load().map { if (it != null) it.asEntitieUser() else null }
    }

    fun logout() {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            dao.delete()
        }
    }
}
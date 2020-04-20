package br.com.aisdigital.androidchallenge.core.data.interfaces

import br.com.aisdigital.androidchallenge.core.domain.model.Authorization
import br.com.aisdigital.androidchallenge.core.domain.model.User

interface UserRemote {
  
    suspend fun getAuthToken(email: String, password: String): Authorization
   
    suspend fun login(token: String): User
}
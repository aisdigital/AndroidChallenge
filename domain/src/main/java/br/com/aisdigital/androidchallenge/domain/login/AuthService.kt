package br.com.aisdigital.androidchallenge.domain.login

import br.com.aisdigital.androidchallenge.domain.user.User

interface AuthService {

    suspend fun authenticate(login: Login): Login

    suspend fun login(login: Login): User
}
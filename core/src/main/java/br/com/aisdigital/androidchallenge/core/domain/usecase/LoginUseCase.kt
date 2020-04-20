package br.com.aisdigital.androidchallenge.core.domain.usecase

import br.com.aisdigital.androidchallenge.core.domain.interfaces.UserRepository

class LoginUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}
package br.com.aisdigital.androidchallenge.core.domain.usecase

import br.com.aisdigital.androidchallenge.core.domain.interfaces.UserRepository

class CheckLoginStatusUseCase(private val repository: UserRepository) {

    suspend operator fun invoke() = repository.isLoggedIn()
}
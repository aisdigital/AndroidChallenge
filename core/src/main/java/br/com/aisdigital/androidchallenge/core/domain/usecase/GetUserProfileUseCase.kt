package br.com.aisdigital.androidchallenge.core.domain.usecase

import br.com.aisdigital.androidchallenge.core.domain.interfaces.UserRepository

class GetUserProfileUseCase(private val repository: UserRepository) {

    suspend operator fun invoke() = repository.getLoggedUser()
}
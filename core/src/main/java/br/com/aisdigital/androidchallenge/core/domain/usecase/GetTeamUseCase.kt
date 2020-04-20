package br.com.aisdigital.androidchallenge.core.domain.usecase

import br.com.aisdigital.androidchallenge.core.domain.interfaces.TeamRepository

class GetTeamUseCase(private val repository: TeamRepository) {

    suspend operator fun invoke(teamId: Long) = repository.getById(teamId)
}
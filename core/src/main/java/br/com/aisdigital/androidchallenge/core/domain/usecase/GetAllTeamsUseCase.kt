package br.com.aisdigital.androidchallenge.core.domain.usecase

import br.com.aisdigital.androidchallenge.core.domain.interfaces.TeamRepository
import br.com.aisdigital.androidchallenge.core.domain.model.Result
import br.com.aisdigital.androidchallenge.core.domain.model.Team

class GetAllTeamsUseCase(private val repository: TeamRepository) {

    suspend operator fun invoke(): Result<List<Team>> {
        return when (val result = repository.getAll()) {
            is Result.Success -> {
                val stories = result.data.sortedBy { it.name }
                Result.Success(stories)
            }
            is Result.Error -> {
                result
            }
        }
    }
}
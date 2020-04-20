package br.com.aisdigital.androidchallenge.core.domain.interfaces

import br.com.aisdigital.androidchallenge.core.domain.model.Result
import br.com.aisdigital.androidchallenge.core.domain.model.Team

interface TeamRepository {

    /**
     *  Get all teams from repository.
     */
    suspend fun getAll(): Result<List<Team>>

    /**
     *  Get identified team from repository.
     *  @param teamId Team id.
     */
    suspend fun getById(teamId: Long): Team
}
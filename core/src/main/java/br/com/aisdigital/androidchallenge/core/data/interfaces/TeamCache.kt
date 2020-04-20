package br.com.aisdigital.androidchallenge.core.data.interfaces

import br.com.aisdigital.androidchallenge.core.domain.model.Team

interface TeamCache {

    /**
     *  Adds all teams to the cache.
     *  @param teams List of teams.
     */
    suspend fun add(teams: List<Team>)

    /**
     *  Get all teams from repository.
     */
    suspend fun getAll(): List<Team>

    /**
     *  Get identified team from repository.
     *  @param teamId Team id.
     */
    suspend fun getById(teamId: Long): Team
}
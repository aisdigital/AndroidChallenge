package br.com.aisdigital.androidchallenge.core.data.interfaces

import br.com.aisdigital.androidchallenge.core.domain.model.Team

interface TeamRemote {

    /**
     *  Get all teams from remote source.
     *  @return List of teams.
     */
    suspend fun getAll(): List<Team>
}
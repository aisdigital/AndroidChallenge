package br.com.aisdigital.androidchallenge.framework

import br.com.aisdigital.androidchallenge.core.data.interfaces.TeamCache
import br.com.aisdigital.androidchallenge.core.domain.model.Team

class TeamDatabaseCache(private val dao: TeamDao) :
    TeamCache {

    override suspend fun add(teams: List<Team>) = dao.insert(teams.map { it.toEntity() })

    override suspend fun getAll() = dao.queryAll().map { it.toDomainModel() }

    override suspend fun getById(teamId: Long) = dao.queryById(teamId).toDomainModel()
}
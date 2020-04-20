package br.com.aisdigital.androidchallenge.core.data.repository

import br.com.aisdigital.androidchallenge.core.data.interfaces.TeamCache
import br.com.aisdigital.androidchallenge.core.data.interfaces.TeamRemote
import br.com.aisdigital.androidchallenge.core.domain.interfaces.TeamRepository
import br.com.aisdigital.androidchallenge.core.domain.model.Result
import br.com.aisdigital.androidchallenge.core.domain.model.Team

class TeamDefaultRepository(private val cache: TeamCache, private val remote: TeamRemote) :
    TeamRepository {

    override suspend fun getAll(): Result<List<Team>> {
        return try {
            // In real world applications we can control cache timeout, include swipe to refresh or
            // other approaches.
            val teams = cache.getAll()
            if (teams.isNotEmpty()) {
                Result.Success(teams)
            } else {
                cache.add(remote.getAll())
                Result.Success(cache.getAll())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getById(teamId: Long) = cache.getById(teamId)
}
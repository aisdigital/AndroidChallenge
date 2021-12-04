package br.com.aisdigital.androidchallenge.repositories

import android.util.Log
import br.com.aisdigital.androidchallenge.data.local.TeamDao
import br.com.aisdigital.androidchallenge.data.local.asEntitieTeam
import br.com.aisdigital.androidchallenge.data.remote.AndroidChallengeApi
import br.com.aisdigital.androidchallenge.data.remote.asDatabaseTeam
import br.com.aisdigital.androidchallenge.entities.Team
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TeamRepository @Inject constructor(
    private val dao: TeamDao,
    private val api: AndroidChallengeApi
) {

    fun getTeams(): Flow<List<Team>> {

        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            refreshTeams()
        }

        return dao.load().map {
            it.asEntitieTeam()
        }
    }

    private suspend fun refreshTeams() {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getTeams()
                dao.save(response.asDatabaseTeam())
            } catch (e: Exception) {
                Log.d("TeamRepository", e.stackTraceToString())
            }
        }
    }
}
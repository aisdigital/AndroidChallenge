package br.com.aisdigital.androidchallenge.repository

import androidx.lifecycle.LiveData
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.networking.TeamsApi
import br.com.aisdigital.androidchallenge.persistence.dao.TeamDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TeamRepository @Inject constructor(
    private val teamDao: TeamDao,
    private val teamsApi: TeamsApi
) {

    fun getTeams() : LiveData<List<Team>> {
        teamsApi.getTeams()
            .subscribeOn(Schedulers.io())
            .doOnSuccess { teamDao.insert(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        return teamDao.getAllTeams()
    }
}
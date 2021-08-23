package br.com.aisdigital.androidchallenge.repository

import android.util.Log
import br.com.aisdigital.androidchallenge.model.Token
import br.com.aisdigital.androidchallenge.model.User
import br.com.aisdigital.androidchallenge.modules.Module
import br.com.aisdigital.androidchallenge.networking.TeamsApi
import br.com.aisdigital.androidchallenge.persistence.dao.UserDao
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val userDao: UserDao,
    private val teamsApi: TeamsApi,
    private val schedulerProvider: Module.SchedulerProvider
) {

    fun login(email: String, pwd: String) : Single<User> =
        teamsApi.makeAuth(email, pwd)
            .subscribeOn(schedulerProvider.io())
            .doOnSuccess { Log.d("LOGIN REPO", "Token received:${it.token}") }
            .flatMap { teamsApi.login(it.token) }
            .doOnSuccess { user -> userDao.insert(user) }
            .doOnError { it.printStackTrace() }
            .observeOn(schedulerProvider.ui())
}
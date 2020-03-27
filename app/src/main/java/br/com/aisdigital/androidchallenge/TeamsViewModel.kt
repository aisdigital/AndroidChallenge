package br.com.aisdigital.androidchallenge

import android.app.Application
import androidx.lifecycle.*
import br.com.aisdigital.androidchallenge.data.team.TeamApi
import br.com.aisdigital.androidchallenge.data.team.TeamRepository
import br.com.aisdigital.androidchallenge.model.Team
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TeamsViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {

    private val repository: TeamRepository

    val teams = MutableLiveData<List<Team>>().apply { value = listOf() }
    val isLoading = MutableLiveData<Boolean>().apply { value = true }
    val message = MutableLiveData<String>().apply { value = "" }

    init {
        val teamApi = Retrofit.Builder()
            .baseUrl("https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TeamApi::class.java)

        repository = TeamRepository(teamApi)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun fetchTeams() {
        repository.getAll({
            isLoading.postValue(false)
            teams.postValue(it)
        }, {
            message.postValue("Something went wrong while fetching teams list. Please try again!")
            isLoading.postValue(false)
        })
    }

}
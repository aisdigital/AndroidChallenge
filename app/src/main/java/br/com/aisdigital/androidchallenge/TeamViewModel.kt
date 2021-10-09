package br.com.aisdigital.androidchallenge

import Team
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.service.Repository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TeamViewModel constructor(private val repository: Repository)  : ViewModel() {

    val teamList = MutableLiveData<List<Team>>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>().apply { false }

    fun getAllMovies() {
        loadInitialized()
            val response = repository.getAllTeams()
            response.enqueue(object : Callback<List<Team>> {
                override fun onResponse(call: Call<List<Team>>, response: Response<List<Team>>) {
                    loadFinalized()
                    teamList.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Team>>, t: Throwable) {
                    loadFinalized()
                    errorMessage.postValue(t.message)
                }
            })
        }

    private fun loadInitialized() {
        isLoading.value = true
    }

    private fun loadFinalized() {
        isLoading.value = false
    }
}
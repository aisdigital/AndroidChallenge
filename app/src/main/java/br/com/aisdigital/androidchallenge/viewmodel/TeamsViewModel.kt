package br.com.aisdigital.androidchallenge.viewmodel

import android.app.Application
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.App
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.repository.BackendRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamsViewModel(application: Application) : AndroidViewModel(application) {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _success = MutableLiveData<List<Team>>()
    val success: MutableLiveData<List<Team>>
        get() = _success

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    @Inject
    lateinit var backendRepository: BackendRepository
    @Inject
    lateinit var preferences: SharedPreferences
    @Inject
    lateinit var resources: Resources
    
    init {
        getApplication<App>().component.inject(this)
        getTeams()
    }

    fun getTeams() {
            viewModelScope.launch {
                try {
                    _loading.postValue(true)
                    val response = backendRepository.getTeamsAsync()
                    _success.postValue(response)
                    _loading.postValue(false)
                } catch (e: Exception) {
                    _loading.postValue(false)
                    _error.postValue(resources.getString(R.string.msg_erro_http))
                }
        }
    }
}

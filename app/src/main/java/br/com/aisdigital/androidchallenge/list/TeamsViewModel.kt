package br.com.aisdigital.androidchallenge.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.data.TeamsRepository
import br.com.aisdigital.androidchallenge.data.model.Team
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class TeamsViewModel(private val teamRepository: TeamsRepository) : ViewModel() {

    private var _team = MutableLiveData<List<Team>>().apply {
        value = listOf()
    }

    val teams: LiveData<List<Team>> = _team

    fun getTeams() {

        teamRepository.getTeams()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {  }
            .doOnComplete {  }
            .subscribe ({
                _team.value = it
            }, { e ->
               e.printStackTrace()
            })
    }
}

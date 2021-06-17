package br.com.aisdigital.androidchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.Option
import br.com.aisdigital.androidchallenge.service.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TeamViewModel(private val repository: Repository) : ViewModel() {

    internal val teams : MutableLiveData<Option> = MutableLiveData()
    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


    fun getTeams(){
        disposable.add(repository.getTeams()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                teams.postValue(Option.SomeTeam(it))
            }, {
                teams.postValue(Option.None(R.string.error_message))
            }))
    }

}
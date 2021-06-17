package br.com.aisdigital.androidchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.Option
import br.com.aisdigital.androidchallenge.service.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val repository: Repository) : ViewModel() {

    internal val user : MutableLiveData<Option> = MutableLiveData()
    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


    fun login(mail: String, pass : String){

        disposable.add( repository.auth(mail, pass).flatMap {
            repository.login(it.token)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                user.postValue(Option.SomeUser(it))
            }, {
                user.postValue(Option.None(R.string.error_message))
            }))
    }
}
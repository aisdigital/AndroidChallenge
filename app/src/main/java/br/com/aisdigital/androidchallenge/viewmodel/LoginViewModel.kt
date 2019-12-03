package br.com.aisdigital.androidchallenge.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aisdigital.androidchallenge.domain.Auth
import br.com.aisdigital.androidchallenge.domain.Session
import br.com.aisdigital.androidchallenge.domain.User
import br.com.aisdigital.androidchallenge.interactor.LoginInteractor
import io.reactivex.disposables.Disposable

class LoginViewModel (val app: Application) : AndroidViewModel(app) {

    private val interactor = LoginInteractor(app.applicationContext)

    private lateinit var loginDisposable: Disposable

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    val result = MutableLiveData<ChallengeData<User>>()
    val user = MutableLiveData<User>()

    fun login() {

        _dataLoading.value = true

        loginDisposable = interactor.login().subscribe { res, error ->
            _dataLoading.value = false

            if (error != null) {
                result.value = ChallengeData(null, error)
                return@subscribe
            }

            user.value = res.copy()
        }


    }

    override fun onCleared() {
        super.onCleared()
        loginDisposable.dispose()
    }

}
package br.com.aisdigital.androidchallenge.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aisdigital.androidchallenge.domain.Auth
import br.com.aisdigital.androidchallenge.domain.Session
import br.com.aisdigital.androidchallenge.interactor.AuthInteractor
import io.reactivex.disposables.Disposable

class AuthViewModel(val app: Application) : AndroidViewModel(app) {

    private val interactor = AuthInteractor(app.applicationContext)

    private lateinit var authDisposable: Disposable

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val result = MutableLiveData<ChallengeData<Session>>()

    fun authenticate() {
        if (email.value != null && password.value != null) {

            val auth = Auth(email.value!!, password.value!!)
            val sharePref = app.applicationContext.getSharedPreferences("br.com.aisdigital", Context.MODE_PRIVATE)
            sharePref.edit().putString("token", "").apply()

            _dataLoading.value = true

            authDisposable = interactor.authenticate(auth).subscribe { res, error ->
                _dataLoading.value = false

                if (error != null) {
                    result.value = ChallengeData(null, error)
                    return@subscribe
                }

                val sharePref = app.applicationContext.getSharedPreferences("br.com.aisdigital", Context.MODE_PRIVATE)
                sharePref.edit().putString("token", res.token).apply()
                result.value = ChallengeData(res, null)
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        authDisposable.dispose()
    }

}
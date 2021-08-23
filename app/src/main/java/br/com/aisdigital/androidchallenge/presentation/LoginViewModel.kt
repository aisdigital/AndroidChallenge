package br.com.aisdigital.androidchallenge.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    internal val loginResult : MutableLiveData<LoginState> = MutableLiveData()
    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun login(email: String, pwd: String) {
        loginResult.postValue(LoginState.Loading)

        disposable.add(
            repository.login(email, pwd)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribe(
                    { loginResult.postValue(LoginState.Success(it)) },
                    { loginResult.postValue(LoginState.Error(R.string.login_failed)) }
                )
        )
    }
}
package br.com.aisdigital.androidchallenge.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.data.LoginRepository
import br.com.aisdigital.androidchallenge.data.api.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        loginRepository.auth(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {  }
            .doOnComplete {  }
            .subscribe ({
               getUser(it.token)
            }, { e ->
                _loginResult.value =
                    LoginResult(error = R.string.login_failed)
                e.printStackTrace()
            })
    }

    private fun getUser(it: String?) {

        loginRepository.getUSer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {  }
            .doOnComplete {  }
            .subscribe ({
                _loginResult.value = LoginResult(success = LoggedInUserView(displayName = it.name))

            }, { e ->
                _loginResult.value =
                    LoginResult(error = R.string.login_failed)
                e.printStackTrace()
            })

          }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value =
                LoginFormState(
                    usernameError = R.string.invalid_username
                )
        } else if (!isPasswordValid(password)) {
            _loginForm.value =
                LoginFormState(
                    passwordError = R.string.invalid_password
                )
        } else {
            _loginForm.value =
                LoginFormState(
                    isDataValid = true
                )
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}

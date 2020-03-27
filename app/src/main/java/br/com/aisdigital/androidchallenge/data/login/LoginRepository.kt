package br.com.aisdigital.androidchallenge.data.login

import br.com.aisdigital.androidchallenge.model.LoginResponse
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LoginRepository(private val api: LoginApi) {

    fun login(email: String, password: String, success: (LoginResponse) -> Unit, failure: () -> Unit) {
        api.authenticate(email, password)
            .subscribeOn(Schedulers.io())
            .flatMap { authResponse -> api.login(authResponse.token) }
            .subscribeWith(object : DisposableSingleObserver<LoginResponse>() {
                override fun onSuccess(value: LoginResponse?) {
                    success(value!!)
                }

                override fun onError(e: Throwable?) {
                    failure()
                }
            })
    }
}
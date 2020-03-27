package br.com.aisdigital.androidchallenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.aisdigital.androidchallenge.data.PrefsUtils
import br.com.aisdigital.androidchallenge.data.login.LoginApi
import br.com.aisdigital.androidchallenge.data.login.LoginRepository
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LoginRepository;

    val email = MutableLiveData<String>().apply { value = "" }
    val password = MutableLiveData<String>().apply { value = "" }
    val message = MutableLiveData<String>().apply { value = "" }
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val navigateToHome = MutableLiveData<Boolean>().apply { value = false }

    init {
        val loginApi = Retrofit.Builder()
            .baseUrl("https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(LoginApi::class.java)

        repository = LoginRepository(loginApi)
    }

    fun login() {
        val emailValue = email.value
        val passwordValue = password.value

        if (!emailValue.isNullOrBlank() && !passwordValue.isNullOrBlank()) {
            isLoading.value = true

            repository.login(emailValue, passwordValue, {
                if (PrefsUtils(getApplication()).write("logged_user", Gson().toJson(it))) {
                    navigateToHome.postValue(true)
                } else {
                    message.postValue("Something went wrong while saving your user. Please, try again!")
                }
                isLoading.postValue(false)
            }, {
                message.postValue("Something went wrong while signing in. Please, try again!")
                isLoading.postValue(false)
            })
        } else {
            message.postValue("Please, make sure you inform both e-mail and password!")
        }
    }
}
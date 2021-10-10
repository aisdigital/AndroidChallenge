package br.com.aisdigital.androidchallenge

import User
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.service.Repository
import isEmailValid
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel constructor(private val repository: Repository) : ViewModel() {

    val MINIMUN_DIGITS_PASSWORD = 6

    private lateinit var userEmail : String
    private lateinit var userPassword : String
    val errorMessage = MutableLiveData<String>()
    val invalidEmail = MutableLiveData<Boolean>()
    val invalidPassword = MutableLiveData<Boolean>()
    val requiredFields = MutableLiveData<Boolean>()
    val isAuthenticated = MutableLiveData<Boolean>().apply { false }
    val isLoading = MutableLiveData<Boolean>().apply { false }

    private lateinit var user: User

    fun sign(email: String, password: String) {

        this.userEmail = email
        this.userPassword = password

        if (!isValidateFields())
            return

        user = User(userEmail)

        authenticator()
    }

    private fun isValidateFields(): Boolean {

        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            requiredFields.value = true
            return false
        }

        if (!userEmail.isEmailValid()) {
            invalidEmail.value = true
            return false
        }

        if (userPassword.length < MINIMUN_DIGITS_PASSWORD) {
            invalidPassword.value = true
            return false
        }

        return true
    }

    private fun authenticator() {
        loadInitialized()
        handleApiData()
    }

    private fun handleApiData() {
        val response = repository.postAuthentication(user.age, userPassword)
        response.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                loadFinalized()
                login(response.body()!!.token)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                loadFinalized()
                errorMessage.postValue(t.message)
            }
        })
    }

    private fun login(token: String) {
        loadInitialized()
        val response = repository.getLogin(token)
        response.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                loadFinalized()
                if (response.body() != null){
                    isAuthenticated.postValue(true)
                    user = response.body()!!
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                loadFinalized()
                errorMessage.postValue(t.message)
            }
        })
    }

    private fun loadInitialized() {
        isLoading.value = true
    }

    private fun loadFinalized() {
        isLoading.value = false
    }
}
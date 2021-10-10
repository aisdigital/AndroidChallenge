package br.com.aisdigital.androidchallenge

import User
import UserResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.service.Repository
import isEmailValid
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel constructor(private val repository: Repository) : ViewModel() {
    val userEmail = MutableLiveData<String>()
    private val userPassword = MutableLiveData<String>()
    private val errorMessage = MutableLiveData<String>()
    private val isLoading = MutableLiveData<Boolean>().apply { false }

    lateinit var user: User

    fun sign() {
        user = User("asdf")
        authenticator(user, "123")
    }

    private fun validateFields(): Boolean? {
        return userEmail.value?.isEmailValid()

    }

    private fun authenticator(user: User, password: String) {
        loadInitialized()
        handleApiData()
    }

    private fun handleApiData() {
        val response = repository.postAuthentication(user.age, userPassword.value!!)
        response.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                loadFinalized()
                login(response.body()!!.token)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                loadFinalized()
                errorMessage.postValue(t.message)
            }
        })
    }

    private fun login(token: String) {
        loadInitialized()
        val response = repository.getLogin(token)
        response.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                loadFinalized()
                if (response.body() != null)
                    user = response.body()!!.parseUser
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
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
package br.com.aisdigital.androidchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.aisdigital.androidchallenge.LoginViewModel
import br.com.aisdigital.androidchallenge.databinding.ActivityLoginBinding
import br.com.aisdigital.androidchallenge.service.Repository
import br.com.aisdigital.androidchallenge.service.RetrofitService
import br.com.aisdigital.androidchallenge.utils.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    lateinit var viewModel: LoginViewModel

    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUi()

        setViewModel()

        handleObservables()

    }

    private fun handleObservables() {
        binding.btnLogin.setOnClickListener {
            viewModel.sign()
        }
    }

    private fun setUi() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                Repository(
                    retrofitService
                )
            )
        ).get(LoginViewModel::class.java)
    }
}
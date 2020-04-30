package br.com.aisdigital.androidchallenge.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.ViewState
import br.com.aisdigital.androidchallenge.databinding.ActivityLoginBinding
import br.com.aisdigital.androidchallenge.teams.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.loginViewModel = loginViewModel
        binding.activity = this
        setupLoginState()
    }

    private fun setupLoginState() {
        loginViewModel.loginState.observe(this, Observer{ loginState ->
            when(loginState) {
                is ViewState.Loading -> {
                    btn_sign_in.text = ""
                    btn_sign_in.alpha = 0.2f
                    btn_sign_in.isClickable = false
                    progress.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                }
                is ViewState.Failed -> {
                    error_msg.text = loginState.throwable.message
                    btn_sign_in.text = getString(R.string.sign_in)
                    btn_sign_in.alpha = 1.0f
                    btn_sign_in.isClickable = true
                    progress.visibility = View.GONE
                }
            }
        })
    }

    fun signIn() {
        error_msg.text = ""
        loginViewModel.authenticate()
    }
}

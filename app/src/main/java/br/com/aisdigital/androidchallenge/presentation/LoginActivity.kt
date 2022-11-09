package br.com.aisdigital.androidchallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var bind: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_login)
        supportActionBar?.title = "Login"

        viewModel.loginResultLiveData.observe(this) {
            //TODO
        }

        viewModel.loadingStateLiveData.observe(this) { loadingState ->
            when (loadingState.isLoading) {
                true -> bind.progressBar.visibility = View.VISIBLE
                false -> bind.progressBar.visibility = View.GONE
            }
        }

        viewModel.validationErrorLiveData.observe(this) { validationErrorState ->
            bind.emailTextInputLayout.error =
                getString(validationErrorState.emailValidationErrorResourceId)
            bind.passwordTextInputLayout.error =
                getString(validationErrorState.passwordValidationErrorResourceId)
        }

        bind.loginButton.setOnClickListener {
            viewModel.authenticate(
                bind.emailTextInputEditText.text.toString(),
                bind.passwordTextInputEditText.text.toString()
            )
        }
    }
}

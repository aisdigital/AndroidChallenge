package br.com.aisdigital.androidchallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.ActivityLoginBinding
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by inject()
    private lateinit var bind: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_login)
        supportActionBar?.title = "Login"

        viewModel.loginResultStateLiveData.observe(this) { loginResultState ->
            if (loginResultState.success) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Login Error", Toast.LENGTH_LONG).show()
            }
        }
        viewModel.loadingStateLiveData.observe(this) { loadingState ->
            when (loadingState.isLoading) {
                true -> bind.progressBar.visibility = View.VISIBLE
                false -> bind.progressBar.visibility = View.GONE
            }
        }

        viewModel.validationErrorStateLiveData.observe(this) { validationErrorState ->
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

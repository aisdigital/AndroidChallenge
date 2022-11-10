package br.com.aisdigital.androidchallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

        displayInitialState()
        observeLiveDatas()
        setupListeners()
    }

    private fun setupListeners() {
        bind.resetButton.setOnClickListener {
            viewModel.clearLocalData()
            displayInitialState()
        }

        bind.loginButton.setOnClickListener {
            viewModel.authenticate(
                bind.emailTextInputEditText.text?.toString(),
                bind.passwordTextInputEditText.text?.toString()
            )
        }
    }

    private fun observeLiveDatas() {
        viewModel.loginResultStateLiveData.observe(this) { loginResultState ->
            when (loginResultState.success) {
                true -> {
                    displaySuccessState(
                        loginResultState.clientName ?: getString(R.string.empty),
                        loginResultState.successMessageResourceId
                    )
                }
                false -> {
                    displayErrorState(loginResultState.errorMessageResourceId)

                }
            }
        }
        viewModel.loadingStateLiveData.observe(this) { loadingState ->
            when (loadingState.isLoading) {
                true -> displayLoadingState()
                false -> displayInitialState()
            }
        }

        viewModel.validationErrorStateLiveData.observe(this) { validationErrorState ->
            bind.emailTextInputLayout.error =
                getString(validationErrorState.emailValidationErrorResourceId)
            bind.passwordTextInputLayout.error =
                getString(validationErrorState.passwordValidationErrorResourceId)
        }
    }


    private fun displayInitialState() {
        supportActionBar?.title = getString(R.string.login)
        displayFormsAndButtonView(View.VISIBLE)
        displayLoadingView(View.GONE)
        displayResultView(View.GONE)
    }

    private fun displayLoadingState() {
        displayLoadingView(View.VISIBLE)
        displayFormsAndButtonView(View.GONE)
        displayResultView(View.GONE)
    }

    private fun displaySuccessState(clientName: String, successMessageResourceId: Int) {
        supportActionBar?.title = getString(R.string.hello_client_name, clientName)
        bind.resultContentTextView.text = getString(successMessageResourceId)
        displayResultView(View.VISIBLE)
        displayLoadingView(View.GONE)
        displayFormsAndButtonView(View.GONE)
    }

    private fun displayErrorState(errorMessageResourceId: Int) {
        bind.resultContentTextView.text = getString(errorMessageResourceId)
        displayResultView(View.VISIBLE)
        displayLoadingView(View.GONE)
        displayFormsAndButtonView(View.GONE)
    }

    private fun displayFormsAndButtonView(visibility: Int) {
        bind.emailTextInputLayout.visibility = visibility
        bind.passwordTextInputLayout.visibility = visibility
        bind.loginButton.visibility = visibility
    }

    private fun displayLoadingView(visibility: Int) {
        bind.progressBar.visibility = visibility
    }

    private fun displayResultView(visibility: Int) {
        bind.resultContentTextView.visibility = visibility
        bind.resetButton.visibility = visibility
    }
}

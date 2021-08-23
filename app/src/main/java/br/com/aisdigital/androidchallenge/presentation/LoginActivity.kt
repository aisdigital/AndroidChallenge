package br.com.aisdigital.androidchallenge.presentation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            if (validateFields()) {
                animateProgressOn()
                viewModel.login(
                    binding.emailField.text.toString(),
                    binding.pwdField.text.toString()
                )
            }
        }

        viewModel.loginResult.observe(
            this,
            {
                when (it) {
                    is LoginState.Error -> showLoginError(it.errorMessageId)
                    is LoginState.Success -> startTeamsActivity()
                    else -> animateProgressOn()
                }
            }
        )
    }

    private fun showLoginError(errorMessageId: Int) {
        animateProgressOff()
        binding.emailField.error = getString(errorMessageId)
    }

    private fun startTeamsActivity() {
        startActivity(Intent(this, TeamsActivity::class.java))
        finish()
    }

    private fun animateProgressOn() {
        binding.loading.alpha = 0f
        binding.loading.visibility = View.VISIBLE
        binding.loading.animate().alpha(1f).setListener(null)
    }

    private fun animateProgressOff() {
        binding.loading.alpha = 1f
        binding.loading.animate()
            .alpha(0f)
            .setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        binding.loading.visibility = View.GONE
                    }
                }
            )
    }

    private fun validateFields() : Boolean {
        if (binding.emailField.text.toString().isEmpty()) {
            binding.emailField.error = getString(R.string.login_empty_field)
            return false
        }

        if (binding.pwdField.text.toString().isEmpty()) {
            binding.pwdField.error = getString(R.string.login_empty_field)
            return false
        }

        return true;
    }
}

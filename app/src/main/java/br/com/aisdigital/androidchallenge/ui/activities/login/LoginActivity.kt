package br.com.aisdigital.androidchallenge.ui.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.ActivityLoginBinding
import br.com.aisdigital.androidchallenge.service.Repository
import br.com.aisdigital.androidchallenge.service.retrofit.RetrofitService
import br.com.aisdigital.androidchallenge.ui.activities.teams.TeamsActivity
import br.com.aisdigital.androidchallenge.ui.activities.ViewModelFactory

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

        viewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading.let {
                if (isLoading) binding.progressbar.visibility = View.VISIBLE
                else binding.progressbar.visibility = View.GONE
            }
        })

        viewModel.invalidEmail.observe(this, Observer { isInvalid ->
            isInvalid.let {
                if (isInvalid) binding.txtUserEmail.error = getString(R.string.label_invalid_email)
                else binding.txtUserEmail.error = null
            }
        })

        viewModel.invalidPassword.observe(this, Observer { isInvalid ->
            isInvalid.let {
                if (isInvalid) binding.txtUserPassword.error =
                    getString(R.string.label_invalid_password)
                else binding.txtUserPassword.error = null
            }
        })

        viewModel.isAuthenticated.observe(this, Observer { isInvalid ->
            isInvalid.let {
                val intent = Intent(this, TeamsActivity::class.java)
                startActivity(intent)
            }
        })

        viewModel.requiredFields.observe(this, Observer { isRequired ->
            isRequired.let {
                if (isRequired) binding.txtRequiredFields.visibility = View.VISIBLE
                else binding.txtRequiredFields.visibility = View.GONE
            }
        })
    }

    private fun setUi() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            handleSign()
        }

    }

    private fun handleSign() {
        binding.txtRequiredFields.visibility = View.GONE

        viewModel.sign(
            binding.txtUserEmail.text.toString(),
            binding.txtUserPassword.text.toString()
        )
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                Repository(
                    retrofitService
                )
            )
        ).get(LoginViewModel::class.java)
    }
}
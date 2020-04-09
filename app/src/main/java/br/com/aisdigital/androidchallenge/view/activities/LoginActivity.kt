package br.com.aisdigital.androidchallenge.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.extensions.bindingContentView
import br.com.aisdigital.androidchallenge.extensions.observe
import br.com.aisdigital.androidchallenge.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity: AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingContentView(R.layout.activity_login).run {
            setVariable(BR.loginViewModel, viewModel)
            lifecycleOwner = this@LoginActivity
        }
    }

    private fun setupObservable() {
        viewModel.run {
            observe(user) {
                it?.run {
                    startActivity(
                        Intent(
                            this@LoginActivity,
                            TeamsActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        setupObservable()
    }
}
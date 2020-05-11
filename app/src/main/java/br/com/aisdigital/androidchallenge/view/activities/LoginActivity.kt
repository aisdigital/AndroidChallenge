package br.com.aisdigital.androidchallenge.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.extensions.bindingContentView
import br.com.aisdigital.androidchallenge.internal.AppRouter
import br.com.aisdigital.androidchallenge.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel {
        parametersOf(AppRouter(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_login).apply {
            setVariable(BR.viewModel, viewModel)
        }
    }
}

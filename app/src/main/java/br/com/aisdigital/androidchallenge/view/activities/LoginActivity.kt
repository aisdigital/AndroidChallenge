package br.com.aisdigital.androidchallenge.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.extensions.bindingContentView
import br.com.aisdigital.androidchallenge.internal.AppRouter
import br.com.aisdigital.androidchallenge.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel {
        parametersOf(AppRouter(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupListener()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_login).apply {
            setVariable(BR.viewModel, viewModel)
        }
    }

    private fun setupListener() {
        aLogin_etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.doLogin()
                true
            } else {
                false
            }
        }
    }

    companion object {

        fun getIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)

    }
}

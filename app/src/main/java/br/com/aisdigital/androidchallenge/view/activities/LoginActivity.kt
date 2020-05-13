package br.com.aisdigital.androidchallenge.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.extensions.bindingContentView
import br.com.aisdigital.androidchallenge.extensions.hideKeyboard
import br.com.aisdigital.androidchallenge.extensions.observe
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
        setupObservable()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_login).apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = this@LoginActivity
        }
    }

    private fun setupObservable() {
        viewModel.run {
            observe(success) { success ->
                success?.takeIf { it }?.run {
                    finish()
                }
            }

            observe(errorMsg) {
                it?.run {
                    showError(this)
                }
            }
        }
    }

    private fun setupListener() {
        aLogin_etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                viewModel.doLogin()
                true
            } else {
                false
            }
        }
    }

    private fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}

fun FragmentActivity.startLoginActivity() {
    this.startActivity(Intent(this, LoginActivity::class.java))
}

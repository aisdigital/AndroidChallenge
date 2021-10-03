package br.com.aisdigital.androidchallenge.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.ActivityLoginBinding
import br.com.aisdigital.androidchallenge.di.loadKoinModules
import br.com.aisdigital.androidchallenge.domain.repository.Router
import br.com.aisdigital.androidchallenge.viewmodel.login.LoginViewModel
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val router: Router by inject {
        parametersOf(this)
    }

    private val mViewModel: LoginViewModel by viewModel {
        parametersOf(router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@LoginActivity)
            loadKoinModules()
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this@LoginActivity
        binding.setVariable(BR.viewModel, mViewModel)

        setEditorAction()
    }

    private fun setEditorAction() {
        binding.loginPasswordInputEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                mViewModel.onLoginClick()
                true
            } else {
                false
            }
        }
    }

}

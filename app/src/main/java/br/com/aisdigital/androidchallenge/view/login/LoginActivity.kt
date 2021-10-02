package br.com.aisdigital.androidchallenge.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.ActivityLoginBinding
import br.com.aisdigital.androidchallenge.di.loadKoinModules
import br.com.aisdigital.androidchallenge.domain.repository.AuthInteractor
import br.com.aisdigital.androidchallenge.domain.repository.LoginInteractor
import br.com.aisdigital.androidchallenge.view.home.HomeActivity
import br.com.aisdigital.androidchallenge.viewmodel.login.LoginViewModel
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val authInteractor: AuthInteractor by inject()

    private val loginInteractor: LoginInteractor by inject()

    private val mViewModel: LoginViewModel by viewModel {
        parametersOf(authInteractor, loginInteractor)
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

        setObserver()
    }

    private fun setObserver() {
        mViewModel.shouldNavigateToHome.observe(this, {
            if (it) {
                navigateToHome()
            }
        })
    }

    private fun navigateToHome() {
        val intent = Intent(applicationContext, HomeActivity::class.java)
        Bundle(1).apply {
            putParcelable(HomeActivity.HOME_LOGIN_EXTRAS, mViewModel.loginResponse.value)
            intent.putExtras(this)
        }
        startActivity(intent)
    }
}

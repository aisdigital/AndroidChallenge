package br.com.aisdigital.androidchallenge.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.aisdigital.androidchallenge.viewmodel.MainViewModel
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getToken()

        setListeners()
        setupObservables()
    }

    private fun setListeners() {
        mBinding.btnEnter.setOnClickListener {
            mainViewModel.email.value = mBinding.tietEmailValue.text.toString()
            mainViewModel.password.value = mBinding.tietPasswordValue.text.toString()
            mainViewModel.verifyLoginInfo()
        }
    }

    private fun setupObservables() {
        mainViewModel.saveToken().observe(this, { token ->

            with(getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE).edit()) {
                putString(TOKEN_KEY, token)
                apply()
            }
        })

        mainViewModel.isEmailValid.observe(this, { isValid ->
            if (isValid) {
                mBinding.tilEmailHint.isErrorEnabled = false
            } else {
                mBinding.tilEmailHint.error = getString(R.string.invalid_email)
            }
        })

        mainViewModel.isPasswordEmpty.observe(this, { isEmpty ->
            if (isEmpty) {
                mBinding.tilPasswordHint.error = getString(R.string.emptyPassword)
            } else {
                mBinding.tilPasswordHint.isErrorEnabled = false
            }
        })
    }

    companion object {
        const val SHARED_PREFS = "br.com.aisdigital.androidchallenge.V6n-lXj3r8wsxFVxQLVbFg"
        const val TOKEN_KEY = "TOKEN"
    }
}
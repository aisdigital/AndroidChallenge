package br.com.aisdigital.androidchallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var bind: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_login)
        supportActionBar?.title = "Login"
    }
}

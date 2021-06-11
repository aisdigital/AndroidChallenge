package br.com.aisdigital.androidchallenge.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.observe
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.domain.model.UserModel
import br.com.aisdigital.androidchallenge.presentation.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val bttLogin = findViewById<Button>(R.id.bttLogin)

        bttLogin.setOnClickListener {

            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()

            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                setObservers()
                viewModel.login(email, password)
            }
        }
    }

    private fun setObservers() {
        viewModel.user.observe(this) {
            callOtherActivity(it)
        }
    }

    private fun callOtherActivity(user: UserModel) {

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("user", user)
        }
        startActivity(intent)
    }
}
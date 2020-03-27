package br.com.aisdigital.androidchallenge

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.aisdigital.androidchallenge.data.PrefsUtils
import br.com.aisdigital.androidchallenge.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        if (PrefsUtils(application).exists("logged_user")) {
            navigateToTeams()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = LoginViewModel(application)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.message.observe(this, Observer { message ->
            if (!message.isNullOrBlank()) {
                Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
            }
        })

        viewModel.navigateToHome.observe(this, Observer { navigate ->
            if (navigate) {
                navigateToTeams()
            }
        })
    }

    private fun navigateToTeams() {
        val intent = Intent(this, TeamsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}

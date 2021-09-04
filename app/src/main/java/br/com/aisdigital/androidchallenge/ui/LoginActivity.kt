package br.com.aisdigital.androidchallenge.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.factory.ViewModelFactory
import br.com.aisdigital.androidchallenge.factory.ViewModelTeams
import br.com.aisdigital.androidchallenge.helpers.Status
import br.com.aisdigital.androidchallenge.repository.Repository

class LoginActivity : AppCompatActivity() {
    lateinit var buttonLogin: Button
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewModel: ViewModelTeams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                Repository(ApiClient.RetrofitBuilder.endpoints)

            )

        )[ViewModelTeams::class.java]

        buttonLogin=findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {

            getToken()
        }
    }


    private fun getToken() {
        viewModel.getToken().observe(this) { callback ->

            when (callback.status) {
                Status.Loading -> {


                }
                Status.Success -> {


                    callback.let { response ->

                        val token = response.data!!

                        saveToken(token.token)

                        getUser()


                    }
                }
                Status.Error -> {


                }
            }
        }

    }

    private fun getUser() {
        viewModel.getUser(loadToken()!!).observe(this) { callback ->

            when (callback.status) {
                Status.Loading -> {

                }
                Status.Success -> {


                    callback.let { response ->

                        val user = response.data!!


                        val intent =
                            Intent(applicationContext, UserActivity::class.java)

                        intent.putExtra("user", user)
                        startActivity(intent)



                    }
                }
                Status.Error -> {

                    /*
                                        Toast.makeText(this, "Error to load the data.", Toast.LENGTH_SHORT).show()
                    */
                }
            }
        }

    }



    private fun saveToken(token: String) {


        sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);

        val editor = sharedPreferences.edit()

        editor.apply {
            putString("STRING_KEY", token)
        }.apply()

    }

    private fun loadToken(): String? {
        sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedToken = sharedPreferences.getString("STRING_KEY", null);
        return savedToken

    }
}
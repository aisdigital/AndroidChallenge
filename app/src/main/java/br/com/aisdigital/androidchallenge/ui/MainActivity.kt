package br.com.aisdigital.androidchallenge.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.aisdigital.androidchallenge.utils.MyViewModelFactory
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.TeamViewModel
import br.com.aisdigital.androidchallenge.databinding.ActivityMainBinding
import br.com.aisdigital.androidchallenge.service.Repository
import br.com.aisdigital.androidchallenge.service.RetrofitService

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: TeamViewModel

    private val retrofitService = RetrofitService.getInstance()

    private lateinit var binding: ActivityMainBinding
    val adapter = TeamAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                Repository(
                    retrofitService
                )
            )
        )
            .get(
                TeamViewModel::class.java
            )

        binding.recyclerview.adapter = adapter

        viewModel.teamList.observe(this, Observer { adapter.setTeamsList( it)})

        viewModel.errorMessage.observe(this, Observer {  })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading != null) {
                if (isLoading)
                     binding.progressbar.visibility = View.VISIBLE
                else
                    binding.progressbar.visibility = View.GONE
            }
        })

        viewModel.getAllMovies()

    }
}

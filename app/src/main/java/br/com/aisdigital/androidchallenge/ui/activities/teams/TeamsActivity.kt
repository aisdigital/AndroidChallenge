package br.com.aisdigital.androidchallenge.ui.activities.teams

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.aisdigital.androidchallenge.ui.activities.ViewModelFactory
import br.com.aisdigital.androidchallenge.databinding.ActivityMainBinding
import br.com.aisdigital.androidchallenge.service.Repository
import br.com.aisdigital.androidchallenge.service.retrofit.RetrofitService

class TeamsActivity : AppCompatActivity() {

    private lateinit var viewModel: TeamViewModel

    private val retrofitService = RetrofitService.getInstance()

    private lateinit var binding: ActivityMainBinding
    private val adapter =
        TeamAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUi()

        setViewModel()

        handleObservables()

    }

    private fun setUi() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.adapter = adapter
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                Repository(
                    retrofitService
                )
            )
        ).get(TeamViewModel::class.java)

        viewModel.getAllMovies()
    }

    private fun handleObservables() {
        viewModel.teamList.observe(this, Observer { adapter.setTeamsList(it) })

        viewModel.errorMessage.observe(this, Observer { })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading != null) {
                if (isLoading)
                    binding.progressbar.visibility = View.VISIBLE
                else
                    binding.progressbar.visibility = View.GONE
            }
        })

        viewModel.errorMessage.observe(this, Observer { errorMsg ->
            if (errorMsg != null) {
                binding.errorMessage.text = errorMsg
                binding.errorMessage.visibility = View.VISIBLE
            } else {
                binding.errorMessage.text = errorMsg
                binding.errorMessage.visibility = View.GONE
            }
        })
    }
}

package br.com.aisdigital.androidchallenge.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.ActivityHomeBinding
import br.com.aisdigital.androidchallenge.domain.model.login.LoginResponse
import br.com.aisdigital.androidchallenge.view.adapter.TeamAdapter
import br.com.aisdigital.androidchallenge.viewmodel.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val mAdapter by lazy {
        TeamAdapter(this)
    }

    private val mViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this@HomeActivity
        binding.setVariable(BR.viewModel, mViewModel)

        loadLoginResponse()
        setupMovimentationList()
        observeTeams()
    }

    override fun onResume() {
        super.onResume()
        mViewModel.onResume()
    }

    private fun loadLoginResponse() {
        intent?.run {
            val loginResponse =
                getParcelableExtra<LoginResponse>(HOME_LOGIN_EXTRAS) ?: throw IllegalStateException(
                    "login response cannot be null"
                )
            mViewModel.load(loginResponse)
        }
    }

    private fun setupMovimentationList() {
        binding.homeTeamsList.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = mAdapter
        }
    }

    private fun observeTeams() {
        mViewModel.teamsList.observe(this, {
            if (it.isNotEmpty()) {
                mAdapter.updateList(it)
            }
        })
    }

    companion object {
        const val HOME_LOGIN_EXTRAS = "HOME_LOGIN_EXTRAS"
    }
}
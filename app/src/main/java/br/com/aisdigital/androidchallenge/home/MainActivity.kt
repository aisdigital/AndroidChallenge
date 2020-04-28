package br.com.aisdigital.androidchallenge.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.TeamPresentation
import br.com.aisdigital.androidchallenge.ViewState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: TeamsViewModel by viewModel()
    private lateinit var teamsAdapter: TeamListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.teams.observe(this, Observer { viewState ->
            when (viewState) {
                is ViewState.Loading -> showProgress()
                is ViewState.Success -> showContent(viewState.data)
                is ViewState.Failed -> showErrorContent()
            }
        })

        viewModel.loadTeams()

        bt_retry.setOnClickListener {
            viewModel.loadTeams()
        }
    }

    private fun showErrorContent() {
        updateUi(showError = true)
    }

    private fun showContent(data: List<TeamPresentation>) {
        teamsAdapter = TeamListAdapter()
        teamsAdapter.teams = data
        teamsAdapter.notifyDataSetChanged()

        list.adapter = teamsAdapter

        updateUi(showList = true)
    }

    private fun updateUi(showProgress:Boolean = false, showList:Boolean = false, showError:Boolean = false) {
        progress.visibility = if (showProgress) View.VISIBLE else View.GONE
        list.visibility = if (showList) View.VISIBLE else View.GONE
        error.visibility = if (showError) View.VISIBLE else View.GONE
    }

    private fun showProgress() {
        updateUi(true)
    }
}

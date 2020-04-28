package br.com.aisdigital.androidchallenge.teams

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
    private lateinit var teamList: List<TeamPresentation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val menuItem: MenuItem? = menu?.findItem(R.id.search_icon)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = menuItem?.actionView as SearchView
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView?.queryHint = "Search here!"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {

                text?.let {query ->

                    if (query.isNotEmpty()) {
                        teamsAdapter.teams = if(::teamList.isInitialized) teamList else listOf()
                        val teamsFiltered = teamsAdapter.teams.filter { teamPresentation ->
                            teamPresentation.name.contains(text, true)
                                    || teamPresentation.city.contains(text, true)
                                    || teamPresentation.descr.contains(text, true)
                        }

                        teamsAdapter.teams = teamsFiltered
                        teamsAdapter.notifyDataSetChanged()

                    } else {
                        showContent()
                    }

                    return true
                }

                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun setupToolbar() {
        toolbar.title = "Teams"
        setSupportActionBar(toolbar)
    }

    private fun setupViewModel() {
        viewModel.teams.observe(this, Observer { viewState ->
            when (viewState) {
                is ViewState.Loading -> showProgress()
                is ViewState.Success -> {
                    teamList = viewState.data
                    showContent()
                }
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

    private fun showContent() {
        teamsAdapter = TeamListAdapter()
        teamsAdapter.teams = if(::teamList.isInitialized) teamList else listOf()
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

package br.com.aisdigital.androidchallenge.teams

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import br.com.aisdigital.androidchallenge.AboutActivity
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.TeamPresentation
import br.com.aisdigital.androidchallenge.ViewState
import br.com.aisdigital.androidchallenge.login.LoginActivity
import br.com.aisdigital.androidchallenge.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_teams_list.*
import kotlinx.android.synthetic.main.include_layout_error.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TeamsListActivity : AppCompatActivity() {

    private val viewModel: TeamsViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var teamsAdapter: TeamListAdapter
    private lateinit var teamList: List<TeamPresentation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams_list)
        setupToolbar()
        setupViewModel()
        setupNavMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.appbar_menu, menu)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
            R.id.logout -> {
                loginViewModel.logout()
                val intent = Intent(this, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                }

                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        toolbar.title = "Teams"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    private fun setupNavMenu() {
        val toogle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.open_menu,
            R.string.close_menu
        )

        drawer_layout.addDrawerListener(toogle)
        toogle.syncState()
        nav_view.setNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.about -> {
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                }
                R.id.logout -> {
                    loginViewModel.logout()
                    val intent = Intent(this, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }

                    startActivity(intent)
                    finish()
                }
            }
            true
        }

        val user = loginViewModel.getUserLoggedIn()
        user?.let {
            val navHeader = nav_view.getHeaderView(0)
            navHeader.findViewById<TextView>(R.id.name).text = it.name
            navHeader.findViewById<TextView>(R.id.user_data).text = String.format(Locale.getDefault(), "%s, %s", it.age, it.gender.name)
        }
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

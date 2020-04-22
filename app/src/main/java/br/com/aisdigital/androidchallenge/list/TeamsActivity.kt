package br.com.aisdigital.androidchallenge.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.login.TeamsViewModelFactory

import kotlinx.android.synthetic.main.activity_teams.*
import kotlinx.android.synthetic.main.content_teams.*

class TeamsActivity : AppCompatActivity() {
    private var adapter : TeamsAdapter? = null
    private lateinit var teamsViewModel : TeamsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)
        setSupportActionBar(toolbar)

        teamsViewModel = ViewModelProviders.of(this, TeamsViewModelFactory()).get(TeamsViewModel::class.java)

        adapter = TeamsAdapter{

        }
        recyclerView.layoutManager = LinearLayoutManager(this!!)
        recyclerView.adapter = adapter
        teamsViewModel.teams.observe(this , androidx.lifecycle.Observer {
            adapter?.list = it
            adapter?.notifyDataSetChanged()
        })

        teamsViewModel.getTeams()
    }

}

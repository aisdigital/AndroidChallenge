package br.com.aisdigital.androidchallenge.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.Team

class ChallengeActivity : AppCompatActivity() {


    lateinit var recycleView: RecyclerView
    lateinit var myAdapter: GitListAdapter
    lateinit var gitViewModel: GitViewModel

    private fun setupViewModel() {

        gitViewModel = ViewModelProvider(this).get(GitViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.challenge_activity)
        recycleView = findViewById(R.id.rv_teams)
        myAdapter = GitListAdapter()
        setupViewModel()
        initRecyclerView()
        createData()
    }


    fun initRecyclerView() {
        recycleView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            myAdapter = GitListAdapter()
            adapter = myAdapter


        }
    }

    fun createData() {

        gitViewModel = ViewModelProvider(this)
            .get(GitViewModel::class.java)

        gitViewModel.getRecyclerListDataObserver()
            .observe(this, Observer<ArrayList<Team>> {
                if (it != null) {
                    //var dados = ArrayList<GitModel>()
                    //dados.add(it)
                    myAdapter.setData(it)
                    myAdapter.notifyDataSetChanged()

                } else {
                    Toast.makeText(this, "Erro no get da API", Toast.LENGTH_LONG)
                        .show()
                }

            })

        var dados = gitViewModel.getRepos()

    }
}
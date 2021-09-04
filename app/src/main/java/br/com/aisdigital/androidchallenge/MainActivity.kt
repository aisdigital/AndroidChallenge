package br.com.aisdigital.androidchallenge

import AdapterPhotos
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.factory.ViewModelFactory
import br.com.aisdigital.androidchallenge.factory.ViewModelTeams
import br.com.aisdigital.androidchallenge.helpers.RecyclerItemClickListener
import br.com.aisdigital.androidchallenge.helpers.Status
import br.com.aisdigital.androidchallenge.helpers.hide
import br.com.aisdigital.androidchallenge.helpers.show
import br.com.aisdigital.androidchallenge.model.TeamModel
import br.com.aisdigital.androidchallenge.repository.Repository
import br.com.aisdigital.androidchallenge.ui.DetailsActivity
import br.com.aisdigital.androidchallenge.ui.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModelTeams
    private lateinit var adapterPhotos: AdapterPhotos
    private lateinit var recyclerTeams: RecyclerView
    private lateinit var progressTeams: ProgressBar
    private var listTeams = arrayListOf<TeamModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                Repository(ApiClient.RetrofitBuilder.endpoints)

            )

        )[ViewModelTeams::class.java]

        recyclerTeams = findViewById(R.id.recyclerPhotos)
        progressTeams = findViewById(R.id.progressPhotos)

        recyclerTeams.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        requestData()

        recyclerTeams.addOnItemTouchListener(
            RecyclerItemClickListener(
                this,
                recyclerTeams,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {

                        val teamSelected: TeamModel = listTeams[position]
                        val intent =
                            Intent(applicationContext, DetailsActivity::class.java)

                        intent.putExtra("team", teamSelected)
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View, position: Int) {}
                    override fun onItemClick(
                        parent: AdapterView<*>?, view: View, position: Int, id: Long
                    ) {
                    }
                })
        )
//

    }


    private fun requestData() {
        adapterPhotos = AdapterPhotos()

        viewModel.getTeams().observe(this) { callback ->

            when (callback.status) {
                Status.Loading -> {
                    progressTeams.show()
                    recyclerTeams.hide()
                }

                Status.Success -> {
                    progressTeams.hide()
                    recyclerTeams.show()

                    callback.let { response ->

                        for (team in response.data!!) {
                            listTeams.add(team)
                        }

                        adapterPhotos.addAll(response.data)
                        recyclerTeams.adapter = adapterPhotos
                    }
                }
                Status.Error -> {
                    progressTeams.hide()
                    recyclerTeams.hide()

                    Toast.makeText(this, "Erro ao carregar os dados.", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_login ->
                startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
        return super.onOptionsItemSelected(item)

    }


}

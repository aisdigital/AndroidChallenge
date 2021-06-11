package br.com.aisdigital.androidchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.domain.model.TeamModel
import br.com.aisdigital.androidchallenge.domain.model.UserModel
import br.com.aisdigital.androidchallenge.presentation.MainViewModel
import br.com.aisdigital.androidchallenge.view.adapters.TeamAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = intent.extras?.getParcelable<UserModel>("user")

        //se nao for null, chama
        user?.let {
            AlertDialog.Builder(this)
                .setTitle("Olá " + it.name)
                .setMessage("Idade: " + it.age + "\n" + "Sexo: " + it.gender)
                .setCancelable(false)
                .setPositiveButton(
                    "Fechar"
                ) { dialog, which ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        setObservers()
        viewModel.getTeams()
    }

    private fun setObservers() {
        viewModel.teamList.observe(this) {
            setListOnScreen(it)
        }
    }

    private fun setListOnScreen(list: List<TeamModel>) {
        findViewById<RecyclerView>(R.id.recyclerViewTeams).apply {
            layoutManager = GridLayoutManager(this@MainActivity, 1)

            adapter = TeamAdapter(list) {

            }
        }
    }
}

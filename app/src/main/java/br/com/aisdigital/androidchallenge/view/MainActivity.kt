package br.com.aisdigital.androidchallenge.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.domain.TeamModel
import br.com.aisdigital.androidchallenge.presentation.MainViewModel
import br.com.aisdigital.androidchallenge.view.adapters.TeamAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

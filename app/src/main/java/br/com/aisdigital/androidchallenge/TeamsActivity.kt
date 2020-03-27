package br.com.aisdigital.androidchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aisdigital.androidchallenge.adapter.TeamAdapter
import br.com.aisdigital.androidchallenge.databinding.ActivityTeamsBinding
import com.google.android.material.snackbar.Snackbar

class TeamsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = TeamsViewModel(application)

        val binding: ActivityTeamsBinding = DataBindingUtil.setContentView(this, R.layout.activity_teams)

        binding.teamsList.adapter = TeamAdapter(emptyList())
        binding.teamsList.layoutManager = LinearLayoutManager(this)
        binding.teamsList.setHasFixedSize(true)

        binding.lifecycleOwner = this
        this.lifecycle.addObserver(viewModel)

        viewModel.teams.observe(this, Observer { teams ->
            (binding.teamsList.adapter as TeamAdapter).setData(teams)
        })

        viewModel.message.observe(this, Observer { message ->
            if (!message.isNullOrBlank()) {
                Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
            }
        })
    }

}

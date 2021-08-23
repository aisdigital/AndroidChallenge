package br.com.aisdigital.androidchallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aisdigital.androidchallenge.databinding.ActivityTeamsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsActivity : AppCompatActivity() {

    private val viewModel: TeamViewModel by viewModels()

    private lateinit var binding: ActivityTeamsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.teamsList.apply {
            layoutManager = LinearLayoutManager(this@TeamsActivity)
            addItemDecoration(DividerItemDecoration(
                this@TeamsActivity,
                DividerItemDecoration.VERTICAL
            ))
        }

        viewModel.getTeams().observe(
            this,
            {
                binding.teamsList.adapter = TeamsAdapter(it)
            })
    }
}

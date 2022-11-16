package br.com.aisdigital.androidchallenge.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.aisdigital.androidchallenge.databinding.ActivityTeamsBinding
import br.com.aisdigital.androidchallenge.viewmodel.TeamsViewModel

class TeamsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityTeamsBinding
    private lateinit var teamsViewModel: TeamsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTeamsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        teamsViewModel = ViewModelProvider(this)[TeamsViewModel::class.java]
        teamsViewModel.getTeams()
    }
}
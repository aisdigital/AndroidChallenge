package br.com.aisdigital.androidchallenge.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.adapter.TeamsAdapter
import br.com.aisdigital.androidchallenge.databinding.ActivityTeamsBinding
import br.com.aisdigital.androidchallenge.domain.model.Team
import br.com.aisdigital.androidchallenge.extensions.bindingContentView
import br.com.aisdigital.androidchallenge.extensions.observe
import br.com.aisdigital.androidchallenge.view.TeamDetailDialog
import br.com.aisdigital.androidchallenge.viewmodel.TeamListViewModel
import kotlinx.android.synthetic.main.activity_teams.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TeamsActivity : AppCompatActivity() {

    private val viewModel: TeamListViewModel by viewModel()
    private val listAdapter: TeamsAdapter by inject {
        parametersOf(
            { team: Team -> TeamDetailDialog.getDialog(
                team
            ).show(supportFragmentManager, "") }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingContentView(R.layout.activity_teams).run {
            setVariable(BR.teamViewModel, viewModel)
            lifecycleOwner = this@TeamsActivity
        }
    }

    private fun setupObservable() {
        viewModel.getData()

        viewModel.run {
            observe(teamList) {
                it?.run {
                    listAdapter.setTeamList(this)
                }
            }

        }
    }

    private fun setupRecycler() {
        main.apply {
            this.adapter = listAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onStart() {
        super.onStart()
        setupRecycler()
        setupObservable()
    }

}
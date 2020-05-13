package br.com.aisdigital.androidchallenge.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.extensions.bindingContentView
import br.com.aisdigital.androidchallenge.extensions.swipeRightTransition
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.layout_appbar.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel {
        parametersOf(intent?.getParcelableExtra(EXTRA_TEAM))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupActionBar()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_detail).apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = this@DetailActivity
        }
    }

    private fun setupActionBar() {
        lAppbar_toolbar.apply {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setNavigationOnClickListener {
                onBackPressed()
                swipeRightTransition()
            }
        }
    }

    companion object {
        const val EXTRA_TEAM = "extra_team"
    }
}

fun FragmentActivity.startDetailActivity(team: Team) {
    startActivity(
        Intent(this, DetailActivity::class.java).putExtra(
            DetailActivity.EXTRA_TEAM, team
        )
    )
}

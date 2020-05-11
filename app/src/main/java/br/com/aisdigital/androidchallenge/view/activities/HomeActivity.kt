package br.com.aisdigital.androidchallenge.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.adapter.TeamListAdapter
import br.com.aisdigital.androidchallenge.extensions.bindingContentView
import br.com.aisdigital.androidchallenge.extensions.observe
import br.com.aisdigital.androidchallenge.internal.AppRouter
import br.com.aisdigital.androidchallenge.model.UserInfo
import br.com.aisdigital.androidchallenge.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class HomeActivity : AppCompatActivity() {

    private val router = AppRouter(this)
    private val adapter = TeamListAdapter(router)

    private val viewModel: HomeViewModel by viewModel {
        parametersOf(intent?.getParcelableExtra(EXTRA_USER_INFO))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)

        setupBinding()
        setupRecycler()
        setupObservable()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_home).apply {
            setVariable(BR.viewModel, viewModel)
        }
    }

    private fun setupRecycler() {
        aHome_rvTeams.apply {
            adapter = this@HomeActivity.adapter
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun setupObservable() {
        viewModel.run {
            observe(teamList) {
                it?.let {
                    adapter.setList(it)
                }
            }
        }
    }

    companion object {
        private const val EXTRA_USER_INFO = "extra_user_info"

        fun getIntent(context: Context, userInfo: UserInfo): Intent =
            Intent(context, HomeActivity::class.java).putExtra(EXTRA_USER_INFO, userInfo)
    }

}

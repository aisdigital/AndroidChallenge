package br.com.aisdigital.androidchallenge.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.events.TeamsLoadedEvent
import br.com.aisdigital.androidchallenge.events.UserLoginEvent
import br.com.aisdigital.androidchallenge.model.Option
import br.com.aisdigital.androidchallenge.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.fragment_team_list.*
import org.greenrobot.eventbus.EventBus
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A fragment representing a list of Items.
 */
class TeamFragment : Fragment() {

    private var columnCount = 1
    private lateinit var userName : String;

    private val teamViewModel : TeamViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            userName = it.getString(ARG_USER_NAME, "Anonymous")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_team_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        welcome.text = "${getString(R.string.welcome)} $userName"

        loading.visibility = View.VISIBLE
        teamViewModel.teams.observe(this, Observer {
            loading.visibility = View.GONE
            when(it){
                is Option.SomeTeam -> render(it)
                else -> EventBus.getDefault().post(TeamsLoadedEvent(it))
            }
        })

        teamViewModel.getTeams()
    }

    private fun render(optionTeam: Option.SomeTeam) {

        with(list) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

            adapter = TeamRecyclerViewAdapter(optionTeam.value, this@TeamFragment)
        }
    }




    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        const val ARG_USER_NAME = "some-user"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int, user : Option.SomeUser) =
            TeamFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                    putString(ARG_USER_NAME, user.value.name)
                }
            }
    }
}
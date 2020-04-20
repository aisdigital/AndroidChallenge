package br.com.aisdigital.androidchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.core.domain.model.Team
import kotlinx.android.synthetic.main.fragment_team_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamListFragment : Fragment() {

    private val viewModel: TeamListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListConfigurations()
        observeViewState()
    }

    private fun setListConfigurations() {
        list.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            renderViewState(it)
        })
    }

    private fun renderViewState(viewState: TeamListViewState) {
        when (viewState) {
            is TeamListViewState.ShowTeams -> showTeams(viewState.teams)
            is TeamListViewState.ShowError -> Toast.makeText(
                requireContext(),
                getString(R.string.please_try_again),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showTeams(teams: List<Team>) {
        val adapter = TeamListAdapter(teams) { selectedTeam ->
            showTeamDetails(selectedTeam)
        }
        list.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun showTeamDetails(selectedTeam: Team) {
        findNavController().navigate(
            R.id.action_teamListFragment_to_teamDetailsFragment,
            bundleOf("teamId" to selectedTeam.id)
        )
    }
}
package br.com.aisdigital.androidchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.core.domain.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_team_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamDetailsFragment : Fragment() {

    private val viewModel: TeamDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.team.observe(viewLifecycleOwner, Observer { team ->
            renderView(team)
        })
        loadTeamDetails()
    }

    private fun loadTeamDetails() {
        arguments?.let {
            viewModel.loadTeamDetails((it.getLong("teamId", 1)))
        }
    }

    private fun renderView(team: Team) {
        Picasso.get().load(team.teamImageUrl).into(teamImage)
        name.text = team.name
        conference.text = getString(R.string.conference, team.conference)
        city.text = team.city
        description.text = team.description
    }
}
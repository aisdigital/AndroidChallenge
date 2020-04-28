package br.com.aisdigital.androidchallenge.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.TeamPresentation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_details.*

class TeamDetailsActivity : AppCompatActivity() {

    private lateinit var teamPresentation: TeamPresentation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        if(!intent.hasExtra("team")) {
            finish()
        }

        actionBar?.title = "Team Details"
        teamPresentation = intent.extras?.getParcelable<TeamPresentation>("team")!!

        populateView()
    }

    private fun populateView() {
        Picasso.get()
        .load(teamPresentation.image)
        .into(team_image)

        team_name.text = teamPresentation.name
        city.text = teamPresentation.city
        conference.text = teamPresentation.conference
        description.text = teamPresentation.descr
    }
}

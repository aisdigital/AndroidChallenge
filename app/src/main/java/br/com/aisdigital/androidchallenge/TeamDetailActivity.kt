package br.com.aisdigital.androidchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import br.com.aisdigital.androidchallenge.utils.Utilities

class TeamDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        val teamName = findViewById<TextView>(R.id.team_name)
        val teamLogo = findViewById<ImageView>(R.id.logoTeam)
        val teamCity = findViewById<TextView>(R.id.teamCity)
        val teamConference = findViewById<TextView>(R.id.conferenceTeam)
        val teamDescription = findViewById<TextView>(R.id.descriptionTeam)
        var mBundle: Bundle? = intent.extras["team"] as Bundle
        teamName.text = mBundle?.get("team_name") as String?
        teamConference.text = mBundle?.get("team_conference") as String?
        teamDescription.text = mBundle?.get("team_description") as String?
        teamCity.text = mBundle?.get("team_city") as String?

        teamLogo.setImageBitmap(Utilities().getTeamImage(mBundle?.get("team_teamImageUrl") as String))
        var backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            super.onBackPressed()
        }
    }
}
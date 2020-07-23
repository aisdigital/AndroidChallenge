package br.com.aisdigital.androidchallenge

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import br.com.aisdigital.androidchallenge.async.team.TeamApiTask
import br.com.aisdigital.androidchallenge.data.models.Team
import br.com.aisdigital.androidchallenge.utils.Utilities
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrincipalActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val headerView = navigationView.getHeaderView(0)
        val navUsername = headerView.findViewById(R.id.nameUser) as TextView
        val navUserEmail = headerView.findViewById(R.id.userEmail) as TextView

        navUsername.text = intent.extras["name"] as String?
        navUserEmail.text = intent.extras["email"] as String?



        val mainLinearLayout = findViewById<LinearLayout>(R.id.cardLayout)
        var teams: List<Team>? = ArrayList()
        try {
            teams =  getTeamsData()
            if(teams != null){
                for (team in teams) {
                    mainLinearLayout.addView(createTeamCard(team))
                }
            }
        }
        catch (e: Exception){
            Log.e("ERROR", e.cause?.message)
        }
    }

    private fun createTeamCard(team: Team): CardView {

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels

        val cardLayout = LinearLayout(this)
        cardLayout.orientation = LinearLayout.HORIZONTAL
        val teamCard = CardView(this)
        teamCard.radius = 30f
        teamCard.setContentPadding(36,36,36,36)
        teamCard.isClickable = true
        teamCard.setOnClickListener {
            val intent = Intent(this, TeamDetailActivity::class.java)
            val mBundle = Bundle()
            mBundle.putString("team_name", team.name)
            mBundle.putString("team_city", team.city)
            mBundle.putString("team_conference", team.conference)
            mBundle.putString("team_teamImageUrl",team.teamImageUrl)
            mBundle.putString("team_description", team.description)
            intent.putExtra("team",mBundle)
            startActivity(intent)
        }
        val teamCardParams = LinearLayout.LayoutParams(
            width - 150,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        teamCardParams.setMargins(16,16,16,16)
        teamCardParams.gravity = Gravity.CENTER
        teamCard.layoutParams = teamCardParams
        teamCard.cardElevation = 30f

        val imageView = ImageView(this)
        imageView.setImageBitmap(Utilities().getTeamImage(team.teamImageUrl))

        val imageViewParams = LinearLayout.LayoutParams(200,200)
        imageView.layoutParams = imageViewParams
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.adjustViewBounds = true
        cardLayout.addView(imageView)


        val teamInfoLayout = LinearLayout(this)
        teamInfoLayout.orientation = LinearLayout.VERTICAL
        val teamInfoParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        teamInfoLayout.layoutParams=teamInfoParams
        teamInfoLayout.setPadding(30,5,5,5)

        val quote = TextView(this)
        quote.text = team.name;
        quote.textSize = 20f
        quote.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC)
        val quoteParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        teamInfoLayout.addView(quote)

        val city = TextView(this)
        city.text = team.city;
        city.textSize = 16f
        city.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
        val cityParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        teamInfoLayout.addView(city)


        cardLayout.addView(teamInfoLayout)
        teamCard.addView(cardLayout)
        return teamCard
    }

    @Throws(java.lang.Exception::class)
    fun getTeamsData(): List<Team>? {
        val json: String? = TeamApiTask()
            .execute().get()
        var myType = object : TypeToken<List<Team>?>() {}.type
        return Gson().fromJson<List<Team>?>(json,myType)
    }


}
package br.com.aisdigital.androidchallenge.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import br.com.aisdigital.androidchallenge.MainActivity
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.TeamModel
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    lateinit var name: TextView
    lateinit var description: TextView
    lateinit var city: TextView
    lateinit var conference: TextView
    lateinit var image: ImageView
    lateinit var buttonBack: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val teamSelected = intent.getSerializableExtra("team") as TeamModel
        initComponents()

        name.text=teamSelected.name
        city.text=teamSelected.city
        conference.text=teamSelected.conference
        description.text=teamSelected.description
        Picasso.get().load(teamSelected.teamImageUrl).into(image)

        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initComponents(){

        name=findViewById(R.id.textViewName)
        city=findViewById(R.id.textViewCity)
        conference=findViewById(R.id.textViewConference)
        description=findViewById(R.id.textViewDescription)
        image=findViewById(R.id.teamImageUrl)
        buttonBack=findViewById(R.id.buttonBack)

    }
}
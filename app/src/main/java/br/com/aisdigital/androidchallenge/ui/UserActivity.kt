package br.com.aisdigital.androidchallenge.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import br.com.aisdigital.androidchallenge.MainActivity
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.User

class UserActivity : AppCompatActivity() {

    lateinit var name: TextView
    lateinit var age: TextView
    lateinit var gender: TextView
    lateinit var buttonBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val teamSelected = intent.getSerializableExtra("user") as User
        initComponents()

        name.text = teamSelected.name
        age.text = teamSelected.age
        gender.text = teamSelected.gender
        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    private fun initComponents() {
        buttonBack=findViewById(R.id.buttonBack)
        name = findViewById(R.id.itemName)
        age = findViewById(R.id.itemAge)
        gender = findViewById(R.id.itemGender)
    }


}
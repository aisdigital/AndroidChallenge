package br.com.aisdigital.androidchallenge

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about.*


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setupClickables()
        setSupportActionBar(toolbar)
        toolbar.title = "About"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupClickables() {
        github_link.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/derlandyb/AndroidChallenge/tree/feature/derlandy_belchior")
            startActivity(intent)
        }
    }
}

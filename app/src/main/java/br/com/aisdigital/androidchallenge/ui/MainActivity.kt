package br.com.aisdigital.androidchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.aisdigital.androidchallenge.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

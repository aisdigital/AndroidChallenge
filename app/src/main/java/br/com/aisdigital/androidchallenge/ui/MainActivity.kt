package br.com.aisdigital.androidchallenge.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.aisdigital.androidchallenge.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavigationView()
        setNavigationVisibility()
    }

    private fun setNavigationView() {
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
    }

    private fun setNavigationVisibility() {
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.teamDetailsFragment) {
                nav_view.visibility = View.GONE
            } else {
                nav_view.visibility = View.VISIBLE
            }
        }
    }
}

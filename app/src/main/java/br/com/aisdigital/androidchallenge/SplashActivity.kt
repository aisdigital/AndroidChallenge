package br.com.aisdigital.androidchallenge

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.aisdigital.androidchallenge.util.SharedPrefUtil

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    override fun onPostResume() {
        super.onPostResume();

        val intent = if (SharedPrefUtil.getToken(this@SplashActivity).isNullOrEmpty()) {
            Intent(this@SplashActivity, MainActivity::class.java);
        } else {
            Intent(this@SplashActivity, TeamListActivity::class.java);
        }
        startActivity(intent);
        finish();
    }
}
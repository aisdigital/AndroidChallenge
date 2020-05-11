package br.com.aisdigital.androidchallenge.internal

import androidx.fragment.app.FragmentActivity
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.UserInfo
import br.com.aisdigital.androidchallenge.view.activities.DetailActivity
import br.com.aisdigital.androidchallenge.view.activities.HomeActivity
import br.com.aisdigital.androidchallenge.view.activities.LoginActivity

class AppRouter(private val activity: FragmentActivity?) {

    fun goToLogin() {
        activity?.run {
            startActivity(LoginActivity.getIntent(this))
        }
    }

    fun goToHome(userInfo: UserInfo) {
        activity?.run {
            startActivity(HomeActivity.getIntent(this, userInfo))
        }
    }

    fun goToDetail(team: Team) {
        activity?.run {
            startActivity(DetailActivity.getIntent(this, team))
        }
    }

}
package br.com.aisdigital.androidchallenge.internal

import androidx.fragment.app.FragmentActivity
import br.com.aisdigital.androidchallenge.extensions.swipeLeftTransition
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.UserInfo
import br.com.aisdigital.androidchallenge.view.activities.startDetailActivity
import br.com.aisdigital.androidchallenge.view.activities.startHomeActivity
import br.com.aisdigital.androidchallenge.view.activities.startLoginActivity
import br.com.aisdigital.androidchallenge.view.activities.startProfileActivity

class AppRouter(private val activity: FragmentActivity?) {

    fun goToLogin() {
        activity?.startLoginActivity()
    }

    fun goToHome(userInfo: UserInfo) {
        activity?.startHomeActivity(userInfo)
    }

    fun goToProfile(userInfo: UserInfo?) {
        userInfo?.let {
            activity?.startProfileActivity(it)
        }
    }

    fun goToDetail(team: Team) {
        activity?.run {
            startDetailActivity(team)
            swipeLeftTransition()
        }
    }
}

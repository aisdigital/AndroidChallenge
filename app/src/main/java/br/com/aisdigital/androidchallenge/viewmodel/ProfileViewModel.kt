package br.com.aisdigital.androidchallenge.viewmodel

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.UserInfo

class ProfileViewModel(private val resources: Resources, val userInfo: UserInfo) : ViewModel() {
    fun getAge(): String = resources.getString(R.string.profile_age_text, userInfo.age)

    fun getAvatar(): Drawable = resources.getDrawable(
        if (userInfo.gender == "MALE") {
            R.drawable.avatar_male
        } else {
            R.drawable.avatar_female
        }
    )
}
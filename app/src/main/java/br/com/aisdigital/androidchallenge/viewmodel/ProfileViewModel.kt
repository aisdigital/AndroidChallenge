package br.com.aisdigital.androidchallenge.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.internal.Gender
import br.com.aisdigital.androidchallenge.model.UserInfo

class ProfileViewModel(private val resources: Resources, val userInfo: UserInfo) : ViewModel() {
    fun getAge(): String = resources.getString(R.string.profile_age_text, userInfo.age)

    fun getAvatar(): Int =
        if (userInfo.gender == Gender.MALE.value) {
            R.drawable.avatar_male
        } else {
            R.drawable.avatar_female
        }
}

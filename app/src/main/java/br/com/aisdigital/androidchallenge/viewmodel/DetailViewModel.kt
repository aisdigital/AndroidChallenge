package br.com.aisdigital.androidchallenge.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.Team

class DetailViewModel(private val resources: Resources, val team: Team) : ViewModel() {

    fun getCity(): String = resources.getString(
        R.string.detail_city, team.city
    )

    fun getConference(): String = resources.getString(
        R.string.detail_conference, team.conference
    )
}

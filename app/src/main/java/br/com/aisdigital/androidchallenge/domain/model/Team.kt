package br.com.aisdigital.androidchallenge.domain.model

import java.io.Serializable

data class Team(
    var city: String? = null,
    var conference: String? = null,
    var description: String? = null,
    var name: String? = null,
    var teamImageUrl: String? = null
) : Serializable
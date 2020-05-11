package br.com.aisdigital.androidchallenge.model

import com.squareup.moshi.Json

data class TeamData(
    @Json(name = "name") val name: String,
    @Json(name = "city") val city: String,
    @Json(name = "conference") val conference: String,
    @Json(name = "teamImageUrl") val imageUrl: String,
    @Json(name = "description") val description: String
)
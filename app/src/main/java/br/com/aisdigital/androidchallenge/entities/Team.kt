package br.com.aisdigital.androidchallenge.entities

class Team(
    val name: String,
    val city: String,
    val conference: String,
    val teamImageUrl: String,
    val description: String,
    var descriptionCollapsed: Boolean = true
)
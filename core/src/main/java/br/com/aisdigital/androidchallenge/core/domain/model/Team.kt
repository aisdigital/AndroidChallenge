package br.com.aisdigital.androidchallenge.core.domain.model

data class Team(
    val id: Long = 0,
    val name: String,
    val city: String,
    val conference: String,
    val teamImageUrl: String,
    val description: String
)
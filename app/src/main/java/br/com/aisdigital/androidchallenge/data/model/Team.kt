package br.com.aisdigital.androidchallenge.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class Team(
    val name: String,
    val city: String,
    val conference: String,
    val teamImageUrl: String,
    val description: String
)

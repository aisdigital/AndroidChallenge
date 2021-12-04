package br.com.aisdigital.androidchallenge.data.remote

import br.com.aisdigital.androidchallenge.data.local.TeamDatabaseEntity
import br.com.aisdigital.androidchallenge.data.local.UserDatabaseEntity
import br.com.aisdigital.androidchallenge.entities.User

data class Token(
    val token: String
)

data class UserRequest(
    val name: String,
    val age: String,
    val gender: String
)

data class TeamRequest(
    val name: String,
    val city: String,
    val conference: String,
    val teamImageUrl: String,
    val description: String
)

fun UserRequest.asDatabaseUser(): UserDatabaseEntity {
    return UserDatabaseEntity(
        email ="",
        name = this.name,
        age = this.age.toInt(),
        gender = this.gender,
        token = null
    )
}

fun List<TeamRequest>.asDatabaseTeam(): List<TeamDatabaseEntity> {
    return map {
        TeamDatabaseEntity(
            name = it.name,
            city = it.city,
            conference = it.conference,
            teamImageUrl = it.teamImageUrl,
            description = it.description
        )
    }
}
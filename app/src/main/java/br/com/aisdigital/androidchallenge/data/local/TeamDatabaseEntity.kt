package br.com.aisdigital.androidchallenge.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.aisdigital.androidchallenge.entities.Team

@Entity
data class TeamDatabaseEntity(
    @PrimaryKey
    val name: String,
    val city: String,
    val conference: String,
    val teamImageUrl: String,
    val description: String
)

fun List<TeamDatabaseEntity>.asEntitieTeam(): List<Team> {
    return map {
        Team(
            name = it.name,
            city = it.city,
            conference = it.conference,
            teamImageUrl = it.teamImageUrl,
            description = it.description
        )
    }
}
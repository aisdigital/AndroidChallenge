package br.com.aisdigital.androidchallenge

import br.com.aisdigital.androidchallenge.entities.Team
import br.com.aisdigital.androidchallenge.entities.User

object Util {

    fun getListOfTeams(size: Int): List<Team> {
        val list = mutableListOf<Team>()

        for (i in 1..size) {
            list.add(
                Team(
                    name = getRandomString(10),
                    city = getRandomString(10),
                    conference = getRandomString(5),
                    teamImageUrl = getRandomString(50),
                    description = getRandomString(250)
                )
            )
        }
        return list
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun getUser(): User {
        return User(
            name = "Jon Doe",
            age = 20,
            gender = "MALE",
            email = "jon_doe@example.com",
            token = "jkrdvjnksdv89qefn8fui31ncdn8"
        )
    }
}
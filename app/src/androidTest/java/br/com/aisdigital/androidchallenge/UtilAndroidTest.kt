package br.com.aisdigital.androidchallenge

import br.com.aisdigital.androidchallenge.data.local.UserDatabaseEntity

object UtilAndroidTest {

    fun getDatabaseUser(): UserDatabaseEntity {
        return UserDatabaseEntity(
            name = "Jon Doe",
            age = 20,
            gender = "MALE",
            email = "jon_doe@example.com",
            token = "jkrdvjnksdv89qefn8fui31ncdn8"
        )
    }
}
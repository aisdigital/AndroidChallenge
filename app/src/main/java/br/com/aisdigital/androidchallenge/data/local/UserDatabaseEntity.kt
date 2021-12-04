package br.com.aisdigital.androidchallenge.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.aisdigital.androidchallenge.entities.User

@Entity
data class UserDatabaseEntity(
    @PrimaryKey
    var email: String,
    val name: String,
    val age: Int,
    val gender: String,
    var token: String?
)

fun UserDatabaseEntity.asEntitieUser(): User {
    return User(
        this.name,
        this.age.toInt(),
        this.gender,
        "",
        null
    )
}
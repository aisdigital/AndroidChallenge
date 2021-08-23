package br.com.aisdigital.androidchallenge.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.User
import br.com.aisdigital.androidchallenge.persistence.dao.TeamDao
import br.com.aisdigital.androidchallenge.persistence.dao.UserDao

@Database(entities = [User::class, Team::class], version = 1)
abstract class MainDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getTeamDao(): TeamDao
}
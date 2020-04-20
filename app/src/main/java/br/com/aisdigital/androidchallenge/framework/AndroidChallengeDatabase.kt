package br.com.aisdigital.androidchallenge.framework

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TeamEntity::class], version = 1)
abstract class AndroidChallengeDatabase : RoomDatabase() {

    abstract fun teamDao(): TeamDao
}
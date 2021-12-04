package br.com.aisdigital.androidchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TeamDatabaseEntity::class, UserDatabaseEntity::class],
    exportSchema = false,
    version = 2
)
abstract class AndroidChallengeDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun userDao(): UserDao
}
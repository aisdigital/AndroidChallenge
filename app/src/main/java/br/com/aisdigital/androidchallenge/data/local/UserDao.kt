package br.com.aisdigital.androidchallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: UserDatabaseEntity)

    @Query("select * from userdatabaseentity limit 1")
    fun load(): Flow<UserDatabaseEntity>

    @Query("delete from userdatabaseentity")
    fun delete()
}
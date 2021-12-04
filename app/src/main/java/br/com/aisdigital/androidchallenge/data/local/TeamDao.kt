package br.com.aisdigital.androidchallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(teams: List<TeamDatabaseEntity>)

    @Query("select * from teamdatabaseentity")
    fun load(): Flow<List<TeamDatabaseEntity>>

    @Query("delete from teamdatabaseentity")
    fun delete()
}
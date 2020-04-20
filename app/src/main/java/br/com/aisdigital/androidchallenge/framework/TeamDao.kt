package br.com.aisdigital.androidchallenge.framework

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TeamDao {

    /**
     *  Insert all teams into Room database.
     *  @param teams List of teams.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(teams: List<TeamEntity>)

    /**
     *  Query all teams from database.
     */
    @Query("SELECT * FROM teams")
    suspend fun queryAll(): List<TeamEntity>

    /**
     *  Query an identified team.
     *  @param teamId Team id.
     *  @return Team entity.
     */
    @Query("SELECT * FROM teams WHERE id=:teamId")
    suspend fun queryById(teamId: Long): TeamEntity
}
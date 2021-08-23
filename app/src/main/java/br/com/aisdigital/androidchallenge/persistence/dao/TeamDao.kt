package br.com.aisdigital.androidchallenge.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.aisdigital.androidchallenge.model.Team

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(team: Team?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teams: List<Team>?)

    @Query("SELECT * FROM teams")
    fun getAllTeams(): LiveData<List<Team>>
}
package br.com.aisdigital.androidchallenge.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.aisdigital.androidchallenge.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User?)

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser(): User
}
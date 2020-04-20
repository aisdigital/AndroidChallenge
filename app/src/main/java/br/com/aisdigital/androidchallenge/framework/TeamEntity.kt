package br.com.aisdigital.androidchallenge.framework

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val city: String,
    val conference: String,
    @ColumnInfo(name = "team_image_url")
    val teamImageUrl: String,
    val description: String
)
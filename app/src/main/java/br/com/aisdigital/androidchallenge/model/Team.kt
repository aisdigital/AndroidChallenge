package br.com.aisdigital.androidchallenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class Team(@PrimaryKey
                @ColumnInfo(name = "name") val name: String,
                @ColumnInfo(name = "city") val city:String,
                @ColumnInfo(name = "conference") val conference: String,
                @ColumnInfo(name = "teamImageUrl") val teamImageUrl: String,
                @ColumnInfo(name = "description") val description : String)
package br.com.aisdigital.androidchallenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (@PrimaryKey
                 @ColumnInfo(name = "name") val name: String,
                 @ColumnInfo(name = "age") val age: Int,
                 @ColumnInfo(name = "gender") val gender: String)
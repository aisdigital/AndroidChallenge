package br.com.aisdigital.androidchallenge.data.models

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("name") var name:String,
    @SerializedName("city") var city:String,
    @SerializedName("conference") var conference:String,
    @SerializedName("teamImageUrl") var teamImageUrl:String,
    @SerializedName("description") var description:String
)
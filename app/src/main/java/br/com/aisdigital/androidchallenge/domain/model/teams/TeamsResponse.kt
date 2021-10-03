package br.com.aisdigital.androidchallenge.domain.model.teams

import com.google.gson.annotations.SerializedName

data class TeamsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("city") val city: String,
    @SerializedName("conference") val conference: Conference,
    @SerializedName("teamImageUrl") val imageUrl: String,
    @SerializedName("description") val description: String
)

enum class Conference {
    @SerializedName("EAST") EAST,
    @SerializedName("WEST") WEST
}

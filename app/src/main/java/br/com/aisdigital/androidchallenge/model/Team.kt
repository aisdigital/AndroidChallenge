package br.com.aisdigital.androidchallenge.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    @Json(name = "name") val name: String,
    @Json(name = "city") val city: String,
    @Json(name = "conference") val conference: String,
    @Json(name = "teamImageUrl") val teamImageUrl: String,
    @Json(name = "description") val description: String
) : Parcelable

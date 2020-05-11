package br.com.aisdigital.androidchallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Team(
    val name: String,
    val city: String,
    val conference: String,
    val imageUrl: String,
    val description: String
) : Parcelable {

    constructor(data: TeamData) : this(
        name = data.name,
        city = data.city,
        conference = data.conference,
        imageUrl = data.imageUrl,
        description = data.description
    )

}
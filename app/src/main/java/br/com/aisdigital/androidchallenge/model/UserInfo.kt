package br.com.aisdigital.androidchallenge.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfo(
    @Json(name = "name") val name: String,
    @Json(name = "age") val age: String,
    @Json(name = "gender") var gender: String
) : Parcelable

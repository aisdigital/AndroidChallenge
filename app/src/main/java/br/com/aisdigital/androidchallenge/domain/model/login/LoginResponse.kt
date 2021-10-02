package br.com.aisdigital.androidchallenge.domain.model.login

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: String,
    @SerializedName("gender") val gender: String
) : Parcelable
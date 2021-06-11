package br.com.aisdigital.androidchallenge.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    var name: String,
    var age: Int,
    var gender: String
):Parcelable

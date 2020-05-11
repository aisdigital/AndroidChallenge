package br.com.aisdigital.androidchallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserInfo(
    val name: String,
    val age: String,
    val gender: String
) : Parcelable {
    constructor(data: UserInfoData) : this(
        name = data.name,
        age = data.age,
        gender = data.gender.toString()
    )
}

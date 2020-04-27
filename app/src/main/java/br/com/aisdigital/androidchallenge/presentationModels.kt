package br.com.aisdigital.androidchallenge

import android.os.Parcel
import android.os.Parcelable

data class TeamPresentation(
    val name: String,
    val city: String,
    val conference: String,
    val image: String,
    val descr: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(city)
        parcel.writeString(conference)
        parcel.writeString(image)
        parcel.writeString(descr)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TeamPresentation> {
        override fun createFromParcel(parcel: Parcel): TeamPresentation {
            return TeamPresentation(parcel)
        }

        override fun newArray(size: Int): Array<TeamPresentation?> {
            return arrayOfNulls(size)
        }
    }
}
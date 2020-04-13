package com.example.lastfmtopcharts.model.artistdetails

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("#text")
    var text: String?,
    var href: String?,
    var rel: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeString(href)
        parcel.writeString(rel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Link> {
        override fun createFromParcel(parcel: Parcel): Link {
            return Link(parcel)
        }

        override fun newArray(size: Int): Array<Link?> {
            return arrayOfNulls(size)
        }
    }
}
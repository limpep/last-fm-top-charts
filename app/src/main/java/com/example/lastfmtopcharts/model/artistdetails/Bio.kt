package com.example.lastfmtopcharts.model.artistdetails

import android.os.Parcel
import android.os.Parcelable

data class Bio(
    var content: String?,
    var links: Links?,
    var published: String?,
    var summary: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Links::class.java.classLoader),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(content)
        parcel.writeParcelable(links, flags)
        parcel.writeString(published)
        parcel.writeString(summary)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bio> {
        override fun createFromParcel(parcel: Parcel): Bio {
            return Bio(parcel)
        }

        override fun newArray(size: Int): Array<Bio?> {
            return arrayOfNulls(size)
        }
    }
}
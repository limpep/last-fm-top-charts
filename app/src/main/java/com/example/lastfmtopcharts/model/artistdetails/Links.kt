package com.example.lastfmtopcharts.model.artistdetails

import android.os.Parcel
import android.os.Parcelable

data class Links(
    var link: Link?
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readParcelable<Link>(Link::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(link, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Links> {
        override fun createFromParcel(parcel: Parcel): Links {
            return Links(parcel)
        }

        override fun newArray(size: Int): Array<Links?> {
            return arrayOfNulls(size)
        }
    }
}

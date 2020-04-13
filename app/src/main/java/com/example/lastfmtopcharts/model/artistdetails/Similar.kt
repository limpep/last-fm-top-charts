package com.example.lastfmtopcharts.model.artistdetails

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Similar(
    var artist: ArrayList<ArtistX>?
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(ArtistX)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(artist)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Similar> {
        override fun createFromParcel(parcel: Parcel): Similar {
            return Similar(parcel)
        }

        override fun newArray(size: Int): Array<Similar?> {
            return arrayOfNulls(size)
        }
    }
}
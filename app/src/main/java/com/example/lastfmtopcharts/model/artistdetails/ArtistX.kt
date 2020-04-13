package com.example.lastfmtopcharts.model.artistdetails

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class ArtistX(
    var image: ArrayList<ImageX>?,
    var name: String?,
    var url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(ImageX),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(image)
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArtistX> {
        override fun createFromParcel(parcel: Parcel): ArtistX {
            return ArtistX(parcel)
        }

        override fun newArray(size: Int): Array<ArtistX?> {
            return arrayOfNulls(size)
        }
    }
}
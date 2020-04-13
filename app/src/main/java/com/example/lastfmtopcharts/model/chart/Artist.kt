package com.example.lastfmtopcharts.model.chart

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Artist(
    val image: ArrayList<Image>?,
    val listeners: String?,
    val mbid: String?,
    val name: String?,
    val playcount: String?,
    val streamable: String?,
    val url: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Image),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(image)
        parcel.writeString(listeners)
        parcel.writeString(mbid)
        parcel.writeString(name)
        parcel.writeString(playcount)
        parcel.writeString(streamable)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Artist> {
        override fun createFromParcel(parcel: Parcel): Artist {
            return Artist(parcel)
        }

        override fun newArray(size: Int): Array<Artist?> {
            return arrayOfNulls(size)
        }
    }
}
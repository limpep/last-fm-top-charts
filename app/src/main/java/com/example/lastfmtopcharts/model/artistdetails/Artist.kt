package com.example.lastfmtopcharts.model.artistdetails

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Artist(
    var bio: Bio?,
    var image: ArrayList<Image>?,
    var mbid: String?,
    var name: String?,
    var ontour: String?,
    var similar: Similar?,
    var stats: Stats?,
    var streamable: String?,
    var tags: Tags?,
    var url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Bio::class.java.classLoader),
        parcel.createTypedArrayList(Image),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Similar::class.java.classLoader),
        parcel.readParcelable(Stats::class.java.classLoader),
        parcel.readString(),
        parcel.readParcelable(Tags::class.java.classLoader),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(bio, flags)
        parcel.writeTypedList(image)
        parcel.writeString(mbid)
        parcel.writeString(name)
        parcel.writeString(ontour)
        parcel.writeParcelable(similar, flags)
        parcel.writeParcelable(stats, flags)
        parcel.writeString(streamable)
        parcel.writeParcelable(tags, flags)
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
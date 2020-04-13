package com.example.lastfmtopcharts.model.chart

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Artists(
    @SerializedName("@attr")
    val attr: Attr?,
    val artist: ArrayList<Artist>?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Attr::class.java.classLoader),
        parcel.createTypedArrayList(Artist)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(attr, flags)
        parcel.writeTypedList(artist)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Artists> {
        override fun createFromParcel(parcel: Parcel): Artists {
            return Artists(parcel)
        }

        override fun newArray(size: Int): Array<Artists?> {
            return arrayOfNulls(size)
        }
    }
}
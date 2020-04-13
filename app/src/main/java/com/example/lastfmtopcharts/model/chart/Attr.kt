package com.example.lastfmtopcharts.model.chart

import android.os.Parcel
import android.os.Parcelable

data class Attr(
    val page: String?,
    val perPage: String?,
    val total: String?,
    val totalPages: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(page)
        parcel.writeString(perPage)
        parcel.writeString(total)
        parcel.writeString(totalPages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Attr> {
        override fun createFromParcel(parcel: Parcel): Attr {
            return Attr(parcel)
        }

        override fun newArray(size: Int): Array<Attr?> {
            return arrayOfNulls(size)
        }
    }
}
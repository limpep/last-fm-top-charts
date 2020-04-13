package com.example.lastfmtopcharts.model.chart

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val imageURL: String?,
    val size: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageURL)
        parcel.writeString(size)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}
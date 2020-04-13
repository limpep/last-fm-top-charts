package com.example.lastfmtopcharts.model.artistdetails

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ImageX(
    @SerializedName("#text")
    var url: String?,
    var size: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(size)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageX> {
        override fun createFromParcel(parcel: Parcel): ImageX {
            return ImageX(parcel)
        }

        override fun newArray(size: Int): Array<ImageX?> {
            return arrayOfNulls(size)
        }
    }
}
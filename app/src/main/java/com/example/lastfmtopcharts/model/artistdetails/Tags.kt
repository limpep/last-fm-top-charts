package com.example.lastfmtopcharts.model.artistdetails

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Tags(
    var tag: ArrayList<Tag>?
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Tag)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(tag)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tags> {
        override fun createFromParcel(parcel: Parcel): Tags {
            return Tags(parcel)
        }

        override fun newArray(size: Int): Array<Tags?> {
            return arrayOfNulls(size)
        }
    }
}
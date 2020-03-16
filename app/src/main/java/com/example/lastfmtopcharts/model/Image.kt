package com.example.lastfmtopcharts.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val imageURL: String,
    val size: String
)
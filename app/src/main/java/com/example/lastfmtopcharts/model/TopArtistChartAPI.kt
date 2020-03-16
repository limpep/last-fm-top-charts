package com.example.lastfmtopcharts.model

import retrofit2.http.GET

interface TopArtistChartAPI {


    @GET("?method=chart.gettopartists")
    suspend fun getTopArtists(): TopArtistChart
}
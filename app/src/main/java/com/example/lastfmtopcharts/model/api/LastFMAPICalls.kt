package com.example.lastfmtopcharts.model.api

import com.example.lastfmtopcharts.model.artistdetails.ArtistDetail
import com.example.lastfmtopcharts.model.chart.TopArtistChart
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFMAPICalls {


    @GET("?method=chart.gettopartists")
    suspend fun getTopArtists(): TopArtistChart

    @GET("?method=artist.getinfo")
    suspend fun getArtistDetails(@Query("mbid") mbid: String): ArtistDetail
}
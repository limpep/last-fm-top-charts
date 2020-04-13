package com.example.lastfmtopcharts.service

import com.example.lastfmtopcharts.di.DaggerApiComponent
import com.example.lastfmtopcharts.model.api.LastFMAPICalls
import javax.inject.Inject

class LastFMAPIService {

    @Inject
    lateinit var api: LastFMAPICalls

    init {
        DaggerApiComponent.create().inject(this)
    }


    suspend fun getTopArtistChart() = api.getTopArtists()
    suspend fun getArtistDetails(mbid: String) = api.getArtistDetails(mbid)



}
package com.example.lastfmtopcharts.model

import com.example.lastfmtopcharts.di.DaggerApiComponent
import javax.inject.Inject

class TopArtistChartAPIService {

    @Inject
    lateinit var api: TopArtistChartAPI

    init {
        DaggerApiComponent.create().inject(this)
    }


    suspend fun getTopArtistChart() = api.getTopArtists()



}
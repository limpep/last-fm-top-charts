package com.example.lastfmtopcharts.di


import com.example.lastfmtopcharts.model.TopArtistChartAPIService

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: TopArtistChartAPIService)

}
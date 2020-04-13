package com.example.lastfmtopcharts.di


import com.example.lastfmtopcharts.service.LastFMAPIService

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: LastFMAPIService)

}
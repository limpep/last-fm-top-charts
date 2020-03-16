package com.example.lastfmtopcharts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.lastfmtopcharts.di.AppModule
import com.example.lastfmtopcharts.di.DaggerViewModelComponent
import com.example.lastfmtopcharts.model.TopArtistChart
import com.example.lastfmtopcharts.model.TopArtistChartAPIService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var injected = false

    val topArtistChart by lazy { MutableLiveData<TopArtistChart>() }

    constructor(application: Application, test: Boolean = true): this(application) {
        injected = true
    }


    @Inject
    lateinit var topArtistChartAPIService: TopArtistChartAPIService

    init {
        DaggerViewModelComponent
            .builder()
            .appModule(AppModule(getApplication()))
            .build()
            .inject(this)
    }

    val getTopArtistChart = liveData(Dispatchers.IO) {
        val retrieveTopArtistChart = topArtistChartAPIService.getTopArtistChart()

        emit(retrieveTopArtistChart)
    }


}
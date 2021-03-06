package com.example.lastfmtopcharts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.lastfmtopcharts.di.AppModule
import com.example.lastfmtopcharts.di.DaggerViewModelComponent
import com.example.lastfmtopcharts.model.chart.TopArtistChart
import com.example.lastfmtopcharts.service.LastFMAPIService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class MainViewModel(application: Application) : AndroidViewModel(application) {
    constructor(application: Application, test: Boolean = true) : this(application) {
        injected = true
    }

    private var injected = false

    val topArtistChart by lazy { MutableLiveData<TopArtistChart>() }

    val loadError by lazy { MutableLiveData<Boolean>() }

    val loading by lazy { MutableLiveData<Boolean>() }

    @Inject
    lateinit var lastFMAPIService: LastFMAPIService

    init {
        if(!injected) {
            DaggerViewModelComponent
                .builder()
                .appModule(AppModule(getApplication()))
                .build()
                .mainViewModel(this)
        }
    }

     fun refresh() {
//        inject()
        loading.value = true
        updateValue(getTopArtistChart.value!!)
    }

    val getTopArtistChart = liveData(Dispatchers.Main) {
        val retrieveTopArtistChart = lastFMAPIService.getTopArtistChart()
        emit(retrieveTopArtistChart)
        updateValue(retrieveTopArtistChart)
    }


    private fun updateValue(value: TopArtistChart) {

           loadError.value = false
           topArtistChart.value = value
           loading.value = false

    }

}
package com.example.lastfmtopcharts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.lastfmtopcharts.di.AppModule
import com.example.lastfmtopcharts.di.DaggerViewModelComponent
import com.example.lastfmtopcharts.model.TopArtistChart
import com.example.lastfmtopcharts.model.TopArtistChartAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var injected = false

    val topArtistChart by lazy { MutableLiveData<TopArtistChart>() }

    val loadError by lazy { MutableLiveData<Boolean>() }

    val loading by lazy { MutableLiveData<Boolean>() }
//
//    constructor(application: Application, test: Boolean = true): this(application) {
//        injected = true
//    }


    @Inject
    lateinit var topArtistChartAPIService: TopArtistChartAPIService


    init {
        DaggerViewModelComponent
            .builder()
            .appModule(AppModule(getApplication()))
            .build()
            .inject(this)
    }

//    fun inject() {
//        if (!injected) {
//            DaggerViewModelComponent
//                .builder()
//                .appModule(AppModule(getApplication()))
//                .build()
//                .inject(this)
//        }
//    }

     fun refresh() {
//        inject()
        loading.value = true
        updateValue(getTopArtistChart.value!!)
    }

    val getTopArtistChart = liveData(Dispatchers.IO) {
        val retrieveTopArtistChart = topArtistChartAPIService.getTopArtistChart()
        emit(retrieveTopArtistChart)
        updateValue(retrieveTopArtistChart)
    }

    private fun updateValue(value: TopArtistChart) {
       CoroutineScope(Dispatchers.Main).launch {
           loadError.value = false
           topArtistChart.value = value
           loading.value = false
       }
    }

}
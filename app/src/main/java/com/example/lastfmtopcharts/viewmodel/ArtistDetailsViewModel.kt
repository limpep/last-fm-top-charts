package com.example.lastfmtopcharts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.lastfmtopcharts.di.AppModule
import com.example.lastfmtopcharts.di.DaggerViewModelComponent
import com.example.lastfmtopcharts.model.artistdetails.ArtistDetail
import com.example.lastfmtopcharts.service.LastFMAPIService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ArtistDetailsViewModel(application: Application) : AndroidViewModel(application) {

    constructor(application: Application, test: Boolean = true) : this(application) {
        injected = true
    }

    private var injected = false

    val artistDetail by lazy { MutableLiveData<ArtistDetail>() }

    val loadError by lazy { MutableLiveData<Boolean>() }

    val loading by lazy { MutableLiveData<Boolean>() }

    var artistId: String? = null

    @Inject
    lateinit var lastFMAPIService: LastFMAPIService


    init {
        if (!injected) {
            DaggerViewModelComponent
                .builder()
                .appModule(AppModule(getApplication()))
                .build()
                .artistDetailViewModel(this)
        }
    }


    val fetch = liveData(Dispatchers.Main) {
        val artistDetail = lastFMAPIService.getArtistDetails(artistId!!)
        emit(artistDetail)
        updateValue(artistDetail)
    }

    private fun updateValue(value: ArtistDetail) {
        loadError.value = false
        artistDetail.value = value
        loading.value = false
    }


}
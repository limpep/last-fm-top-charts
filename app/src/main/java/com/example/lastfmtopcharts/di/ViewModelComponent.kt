package com.example.lastfmtopcharts.di

import com.example.lastfmtopcharts.viewmodel.ArtistDetailsViewModel
import com.example.lastfmtopcharts.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, AppModule::class])
interface ViewModelComponent {

    fun mainViewModel(viewModel: MainViewModel)
    fun artistDetailViewModel(viewModel: ArtistDetailsViewModel)
}
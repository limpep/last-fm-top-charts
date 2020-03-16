package com.example.lastfmtopcharts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lastfmtopcharts.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        getTopArtistChart()
    }



    fun getTopArtistChart() {
        viewModel.getTopArtistChart.observe(this, Observer {
            for (artist in it.artists.artist) {
                Log.d(this.packageName, artist.name)
                Log.d(this.packageName, artist.mbid)
                Log.d(this.packageName, artist.playcount)
                for (images in artist.image) {
                    Log.d(this.packageName, images.imageURL)
                }
            }
        })
    }

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}

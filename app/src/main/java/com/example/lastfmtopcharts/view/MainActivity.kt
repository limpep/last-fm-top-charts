package com.example.lastfmtopcharts.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.lastfmtopcharts.R
import com.example.lastfmtopcharts.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

//    lateinit var viewModel: MainViewModel

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)


//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

//        getTopArtistChart()




    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }



//    fun getTopArtistChart() {
//        viewModel.getTopArtistChart.observe(this, Observer {
//            for (artist in it.artists.artist) {
//                Log.d(this.packageName, artist.name)
//                Log.d(this.packageName, artist.mbid)
//                Log.d(this.packageName, artist.playcount)
//                for (images in artist.image) {
//                    Log.d(this.packageName, images.imageURL)
//                }
//            }
//        })
//    }

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}

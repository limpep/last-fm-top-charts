package com.example.lastfmtopcharts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.lastfmtopcharts.R
import com.example.lastfmtopcharts.databinding.FragmentArtistDetailsBinding
import com.example.lastfmtopcharts.model.artistdetails.ArtistDetail
import com.example.lastfmtopcharts.model.chart.Artist
import com.example.lastfmtopcharts.viewmodel.ArtistDetailsViewModel
import kotlinx.android.synthetic.main.fragment_artist_details.*

class ArtistDetailsFragment : Fragment() {

    private lateinit var viewModel: ArtistDetailsViewModel

    var artist: Artist? = null
    var artistDetails: ArtistDetail? = null

    private lateinit var dataBinding: FragmentArtistDetailsBinding


    private val artistDetailsDataObservable = Observer<ArtistDetail> {artist ->
        artist?.let {
            dataBinding.artist = it.artist
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE

        if (isLoading) {
            listError.visibility = View.GONE
        }
    }

    private val errorLiveDataObserver = Observer<Boolean> { isError ->
        listError.visibility = if (isError) View.VISIBLE else View.GONE
    }

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_artist_details, container, false )

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            artist = ArtistDetailsFragmentArgs.fromBundle(it).artist
        }

        viewModel = ViewModelProvider(this).get(ArtistDetailsViewModel::class.java)
        viewModel.artistId = artist?.mbid

        viewModel.loading.observe(viewLifecycleOwner, loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner, errorLiveDataObserver)
        viewModel.fetch.observe(viewLifecycleOwner, artistDetailsDataObservable)
    }


}

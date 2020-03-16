package com.example.lastfmtopcharts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.lastfmtopcharts.R
import com.example.lastfmtopcharts.model.Artist
import com.example.lastfmtopcharts.model.TopArtistChart
import com.example.lastfmtopcharts.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_artist_list.*
import kotlinx.coroutines.delay

/**
 * A simple [Fragment] subclass.
 */
class ArtistListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private val listAdapter = TopArtistChartAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artist_list, container, false)
    }

    private val artistChartListDataObservable = Observer<TopArtistChart>{ list ->
        list?.let {
            artistTopChartList.visibility = View.VISIBLE
            listAdapter.updateArtistList(it.artists.artist!!)
        }
    }


    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE

        if (isLoading) {
            listError.visibility = View.GONE
            artistTopChartList.visibility = View.GONE
        }
    }

    private val errorLiveDataObserver = Observer<Boolean> { isError ->
        listError.visibility = if (isError) View.VISIBLE else View.GONE
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getTopArtistChart.observe(viewLifecycleOwner, artistChartListDataObservable )
        viewModel.loading.observe(viewLifecycleOwner, loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner, errorLiveDataObserver)

        artistTopChartList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        refreshLayout.setOnRefreshListener {
            artistTopChartList.visibility = View.GONE
            listError.visibility =View.GONE
            loadingView.visibility = View.VISIBLE

            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

//        viewModel.getTopArtistChart.observe(viewLifecycleOwner, Observer {
//            artistTopChartList.visibility = View.VISIBLE
//            listAdapter.updateArtistList(it.artists.artist)
//        })

    }

}

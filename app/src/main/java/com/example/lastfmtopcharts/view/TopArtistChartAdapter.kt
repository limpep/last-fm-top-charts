package com.example.lastfmtopcharts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.lastfmtopcharts.R
import com.example.lastfmtopcharts.databinding.ItemArtistTopChartBinding
import com.example.lastfmtopcharts.model.chart.Artist

class TopArtistChartAdapter(private val topArtistChart: ArrayList<Artist>):
    RecyclerView.Adapter<TopArtistChartAdapter.TopArtistChartViewHolder>(), ArtistClickListener {


    class TopArtistChartViewHolder(var view: ItemArtistTopChartBinding):
        RecyclerView.ViewHolder(view.root)

    fun updateArtistList(artist: List<Artist>) {
        topArtistChart.clear()
        topArtistChart.addAll(artist)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopArtistChartViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemArtistTopChartBinding>(inflator,R.layout.item_artist_top_chart, parent, false)
        return TopArtistChartViewHolder(view)
    }

    override fun getItemCount() = topArtistChart.size

    override fun onBindViewHolder(holder: TopArtistChartViewHolder, position: Int) {
        holder.view.artist = topArtistChart[position]
        holder.view.listener = this
    }

    override fun onClick(v: View) {
        for (artist in topArtistChart) {
            if (v.tag == artist.name) {
                val action = ArtistListFragmentDirections.actionDetail(artist)
                Navigation.findNavController(v).navigate(action)
            }
        }
    }
}
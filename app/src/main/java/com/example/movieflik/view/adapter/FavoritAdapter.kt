package com.example.movieflik.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieflik.R
import com.example.movieflik.databinding.ItemFavBinding
import com.example.movieflik.model.MovieDetail
import com.example.movieflik.network.local.ItemFavorit

class FavoritAdapter (private var listMoviePopular: List<ItemFavorit>) : RecyclerView.Adapter<FavoritAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemFavBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritAdapter.ViewHolder, position: Int) {
        val movie = listMoviePopular[position]

        holder.binding.tvTitleUpcoming.text = movie.judul
        holder.binding.tvReleaseDateUpcoming.text = movie.tanggalRilis

        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w780${movie.pathPoster}")
            .into(holder.binding.ivTopRated)


    }

    override fun getItemCount(): Int {
        return listMoviePopular.size
    }
}

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

class FavoritAdapter(private var listMovieFav: List<ItemFavorit>) : RecyclerView.Adapter<FavoritAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemFavBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: FavoritAdapter.ViewHolder, position: Int) {
        val movie = listMovieFav[position]

        holder.binding.tvTitle.text = movie.title
        holder.binding.tvOverview.text = movie.overview

        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w780${movie.pathPoster}")
            .into(holder.binding.imgPhoto)

        holder.binding.movieDetail.setOnClickListener {
            val id = listMovieFav[position].id
            val imagepath = listMovieFav[position].pathPoster
            val title = listMovieFav[position].title
            val date = listMovieFav[position].releaseDate
            val popularty = listMovieFav[position].popularty
            val overview = listMovieFav[position].overview
            val detail = MovieDetail(id,imagepath,title,date,popularty,overview)

            val data = Bundle()
            data.putParcelable("data_movie",detail)
            Navigation.findNavController(it).navigate(R.id.action_favoritFragment_to_detailFragment,data)

        }
    }
    override fun getItemCount(): Int {
        return listMovieFav.size
    }

}
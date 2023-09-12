package com.example.movieflik.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieflik.R
import com.example.movieflik.databinding.ListMovieBinding
import com.example.movieflik.model.MovieDetail
import com.example.movieflik.model.ResultX

class TopRatedAdapter  (private var listMovieTopRated : List<ResultX>) : RecyclerView.Adapter<TopRatedAdapter.ViewHolder>() {
    class ViewHolder(var binding: ListMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: TopRatedAdapter.ViewHolder, position: Int) {
        holder.binding.tvTitle.text = listMovieTopRated[position].title
        holder.binding.tvOverview.text = listMovieTopRated[position].overview

        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w780${listMovieTopRated[position].poster_path}")
            .into(holder.binding.imgPhoto)

        holder.binding.movieDetail.setOnClickListener {
            val id = listMovieTopRated[position].id
            val imagepath = listMovieTopRated[position].poster_path
            val title = listMovieTopRated[position].title
            val date =listMovieTopRated[position].release_date
            val popularty = listMovieTopRated[position].popularity
            val overview = listMovieTopRated[position].overview
            val detail = MovieDetail(id,imagepath,title,date,popularty,overview)

            val data = Bundle()
            data.putParcelable("data_movie",detail)
            Navigation.findNavController(it).navigate(R.id.action_homeFragment2_to_detailFragment,data)

        }

    }

    override fun getItemCount(): Int {
        return listMovieTopRated.size
    }

}
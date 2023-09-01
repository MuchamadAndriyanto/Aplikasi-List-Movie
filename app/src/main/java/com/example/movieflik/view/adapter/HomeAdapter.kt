package com.example.movieflik.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieflik.R
import com.example.movieflik.databinding.ListItemMovieBinding
import com.example.movieflik.model.MovieDetail
import com.example.movieflik.model.Result

class HomeAdapter (private var listMoviePopular : List<Result>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(var binding: ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {

        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w780${listMoviePopular[position].poster_path}")
            .into(holder.binding.ivBackground)

        holder.binding.movieDetail.setOnClickListener {
            val id = listMoviePopular[position].id
            val imagepath = listMoviePopular[position].poster_path
            val title = listMoviePopular[position].title
            val date =listMoviePopular[position].release_date
            val overview = listMoviePopular[position].overview
            val detail = MovieDetail(id,imagepath,title,date,overview)

            val data = Bundle()
            data.putParcelable("data_movie",detail)
            Navigation.findNavController(it).navigate(R.id.action_homeFragment2_to_detailFragment,data)

        }

    }

    override fun getItemCount(): Int {
        return listMoviePopular.size
    }

}
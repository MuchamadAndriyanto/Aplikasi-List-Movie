package com.example.movieflik.view

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movieflik.R
import com.example.movieflik.databinding.FragmentDetailBinding
import com.example.movieflik.model.MovieDetail
import com.example.movieflik.viewmodel.FavoritViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var context: Context
    private lateinit var viewModel: FavoritViewModel
    private lateinit var movieDetail: MovieDetail
    private var isZoomIn = true
    private lateinit var kenBurnsAnimator: ObjectAnimator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startKenBurnsAnimation()

        movieDetail = arguments?.getParcelable<MovieDetail>("data_movie") as MovieDetail
        Glide.with(context).load("https://image.tmdb.org/t/p/w780${movieDetail.imagepath}")
            .into(binding.ivPosterImage)

        Glide.with(context).load("https://image.tmdb.org/t/p/w780${movieDetail.imagepath}")
            .into(binding.imgPhoto)

        binding.tvNaruto.text = movieDetail.title
        binding.tvRelease.text = movieDetail.date
        binding.tvPopularity.text = movieDetail.popularity.toString()
        binding.tvOverview.text = movieDetail.overview

        viewModel = ViewModelProvider(this).get(FavoritViewModel::class.java)

        // Ambil status favorit dari ViewModel dan terapkan pada movieDetail
        val favoritStatus = viewModel.isFavoriteMovie(movieDetail.id)
        movieDetail.isFavorite = favoritStatus

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnFavorit.setOnClickListener {
            movieDetail.isFavorite = !movieDetail.isFavorite
            if (movieDetail.isFavorite) {
                viewModel.tambahItemFavorit(movieDetail)
            } else {
                viewModel.deleteFavorite(movieDetail)
            }
            updateFavoriteButton()
        }

        updateFavoriteButton()
    }

    private fun updateFavoriteButton() {
        if (movieDetail.isFavorite) {
            binding.btnFavorit.setImageResource(R.drawable.ic_favorite_oren)
        } else {
            binding.btnFavorit.setImageResource(R.drawable.ic_favorite_black)
        }
    }

    private fun startKenBurnsAnimation() {
        kenBurnsAnimator = ObjectAnimator.ofFloat(binding.ivPosterImage, "scaleX", 1.0f, 1.1f)
        kenBurnsAnimator.duration = 5000
        kenBurnsAnimator.repeatCount = ObjectAnimator.INFINITE
        kenBurnsAnimator.repeatMode = ObjectAnimator.REVERSE

        kenBurnsAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            if (value == 1.0f) {
                isZoomIn = !isZoomIn
                kenBurnsAnimator.setFloatValues(if (isZoomIn) 1.0f else 1.1f, if (isZoomIn) 1.1f else 1.0f)
            }
        }

        kenBurnsAnimator.start()
    }
}

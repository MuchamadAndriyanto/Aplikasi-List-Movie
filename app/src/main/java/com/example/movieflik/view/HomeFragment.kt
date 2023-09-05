package com.example.movieflik.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieflik.R
import com.example.movieflik.databinding.FragmentHomeBinding
import com.example.movieflik.model.Result
import com.example.movieflik.view.adapter.HomeAdapter
import com.example.movieflik.view.adapter.TopRatedAdapter
import com.example.movieflik.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var pref: SharedPreferences
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pref = requireActivity().getSharedPreferences("Register", Context.MODE_PRIVATE)
        val username = pref.getString("username", "username")

        binding.tvWelcome.text = "Welcome, $username"

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        moviePopular()
        movieTopRated()
    }

    private fun moviePopular() {
        binding.rvMoviePopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMoviePopular.setHasFixedSize(false)
        viewModel.getmoviePopular()
        viewModel.getlivedatamoviePopular().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.rvMoviePopular.adapter = HomeAdapter(it)
            }
        }
    }

    private fun movieTopRated() {
        binding.rvMovieTopRated.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMovieTopRated.setHasFixedSize(false)
        viewModel.getmovieToprated()
        viewModel.getlivedatamovieTopRated().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.rvMovieTopRated.adapter = TopRatedAdapter(it)
            }
        }
    }
}
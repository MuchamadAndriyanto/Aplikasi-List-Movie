package com.example.movieflik.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieflik.R
import com.example.movieflik.databinding.FragmentFavoritBinding
import com.example.movieflik.databinding.FragmentHomeBinding
import com.example.movieflik.view.adapter.FavoritAdapter
import com.example.movieflik.view.adapter.TopRatedAdapter
import com.example.movieflik.viewmodel.FavoritViewModel


class FavoritFragment : Fragment() {

    private lateinit var binding : FragmentFavoritBinding
    private lateinit var viewModel: FavoritViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FavoritViewModel::class.java)
        movieFavorit()

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun movieFavorit() {
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvFavorite.setHasFixedSize(false)
        viewModel.getAllFavorite()
        viewModel.getAllFavorite().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.rvFavorite.adapter = FavoritAdapter(it)
            }
        }
    }
}
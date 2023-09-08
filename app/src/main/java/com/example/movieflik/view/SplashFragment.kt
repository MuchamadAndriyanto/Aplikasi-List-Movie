package com.example.movieflik.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movieflik.R
import com.example.movieflik.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding
    lateinit var sharedSplash: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        Handler(Looper.myLooper()!!).postDelayed({

            sharedSplash =
                requireContext().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)
            if (sharedSplash.getString("nama", "").equals("")) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment2)
            }else if (sharedSplash.getString("nama", "")!!.isNotEmpty()) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment2)
            }
        }, 3000)
        return binding.root
    }

}
package com.example.movieflik.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movieflik.R
import com.example.movieflik.databinding.FragmentAkunNoLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AkunNoLoginFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentAkunNoLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAkunNoLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_akunNoLoginFragment_to_loginFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        enableAllMenuItems(bottomNavigationView.menu)
    }

    private fun enableAllMenuItems(menu: Menu) {
        for (i in 0 until menu.size()) {
            menu.getItem(i).isEnabled = true
        }
    }
}
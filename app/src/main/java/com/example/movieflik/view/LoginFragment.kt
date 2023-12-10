package com.example.movieflik.view

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.movieflik.R
import com.example.movieflik.databinding.FragmentLoginBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val RC_SIGN_IN = 123

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false) // Inisialisasi binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnGoogle.setOnClickListener {
            startSignInWithGoogle()
        }

    }

    private fun login() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEdiText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            // Login dengan email dan password
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    // Login berhasil
                    Toast.makeText(context, "Login Telah Berhasil", Toast.LENGTH_LONG).show()
                    val sharedPref = requireActivity().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putBoolean("isLoggedIn", true)
                        apply()
                    }
                    Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_homeFragment2)
                } else {
                    // Login gagal
                    Toast.makeText(context, "Email atau Password Kamu Ada Yang Salah", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun startSignInWithGoogle() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == RESULT_OK) {
                // Sign-in successful
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(context, "Login Berhasil sebagai ${user?.displayName}", Toast.LENGTH_SHORT).show()

                // Simpan status login ke SharedPreferences
                val sharedPref = requireActivity().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putBoolean("isLoggedIn", true)
                    apply()
                }
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
            } else {
                // Sign-in failed
                Toast.makeText(context, "Login Gagal. ${response?.error?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
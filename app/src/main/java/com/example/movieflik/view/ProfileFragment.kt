package com.example.movieflik.view

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.movieflik.R
import com.example.movieflik.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedpref: SharedPreferences
    private var isDataVisible = false

    companion object {
        const val IMAGE_REQUEST_CODE = 1_000
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedpref = requireActivity().getSharedPreferences("Register", Context.MODE_PRIVATE)

        val getUserUsername = sharedpref.getString("username", "")
        Log.d("TAG", "Username: $getUserUsername")
        binding.usernameEditText.setText(getUserUsername)

        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        var userEmail: String? = null

        if (currentUser != null) {
            val userEmail = currentUser.email
            binding.tvemail.text = userEmail
            Log.d("TAG", "Email: $userEmail")
        }

        binding.btnClick.setOnClickListener {
            if (isDataVisible) {
                val rotateAnimator = ObjectAnimator.ofFloat(binding.btnClick, "rotation", 90f, 0f)
                rotateAnimator.duration = 250
                rotateAnimator.start()

                // Sembunyikan data
                binding.tvemail.visibility = View.GONE
                binding.tvUsername.visibility = View.GONE
                binding.inputUsername.visibility = View.GONE

                isDataVisible = false
            } else {
                val rotateAnimator = ObjectAnimator.ofFloat(binding.btnClick, "rotation", 0f, 90f)
                rotateAnimator.duration = 250
                rotateAnimator.start()

                // Tampilkan data
                binding.tvemail.visibility = View.VISIBLE
                binding.tvUsername.visibility = View.VISIBLE
                binding.inputUsername.visibility = View.VISIBLE

                isDataVisible = true
            }
        }

        binding.btnUpdate.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val upusername = sharedpref.edit()
            upusername.putString("username", username)
            upusername.apply()

            val userEmail = userEmail ?: ""
            binding.tvemail.text = userEmail

            firebaseAuth.signOut()
            Toast.makeText(context, "Update Username Berhasil", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(binding.root).navigate(R.id.action_profileFragment2_to_homeFragment2)
        }

        binding.btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            val addUser = sharedpref.edit()
            addUser.apply()
            Toast.makeText(context, "Anda Berhasil Logout", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(binding.root).navigate(R.id.action_profileFragment2_to_loginFragment)
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnCamera.setOnClickListener {
            pickImageFromGallery()
        }

        //menyimpan dan mengambil kembali URI gambar
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val imageUriString = sharedPref.getString("imageUri", null)
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            loadImageWithCircleCrop(imageUri)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("imageUri", imageUri.toString())
            editor.apply()

            // Load gambar ke ivPerson dan terapkan CircleCrop
            imageUri?.let { loadImageWithCircleCrop(it) }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    private fun loadImageWithCircleCrop(imageUri: Uri) {
        Glide.with(this)
            .load(imageUri)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(binding.ivPerson)
    }
}

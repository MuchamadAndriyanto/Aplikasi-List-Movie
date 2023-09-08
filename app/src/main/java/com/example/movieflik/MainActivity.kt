package com.example.movieflik

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigation = findViewById(R.id.bottomNavigation)

        setupWithNavController(bottomNavigation, navController)

        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            val currentDestination = navController.currentDestination
            if (currentDestination?.id != menuItem.itemId) {
                if (menuItem.itemId == R.id.favoritFragment || menuItem.itemId == R.id.profileFragment2) {
                    val isLoggedIn = checkLoginStatus()
                    if (!isLoggedIn) {
                        navController.navigate(R.id.akunNoLoginFragment)
                        return@setOnNavigationItemSelectedListener false
                    }
                }
                navController.navigate(menuItem.itemId)
            }
            true
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment2, R.id.favoritFragment, R.id.profileFragment2 -> {
                    bottomNavigation.visibility = View.VISIBLE
                }
                R.id.splashFragment, R.id.loginFragment, R.id.registerFragment, R.id.detailFragment, R.id.akunNoLoginFragment -> {
                    bottomNavigation.visibility = View.GONE
                }
                else -> {
                    bottomNavigation.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun checkLoginStatus(): Boolean {
        val sharedPref = getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("isLoggedIn", false)
    }
}
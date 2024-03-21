package com.github.ebrahimi16153.movieapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.ebrahimi16153.movieapp.R
import com.github.ebrahimi16153.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityMainBinding

    //nav
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inti views
        binding.apply {
            navController = findNavController(viewId = R.id.mainNavHost)
            bottomNav.setupWithNavController(navController = navController)


            // handel which destination show bottom nav
            navController.addOnDestinationChangedListener { _, destination, _ ->

                bottomNav.isVisible = !(destination.id == R.id.splashFragment2 || destination.id == R.id.detailFragment3 || destination.id == R.id.registerFragment)


            }

        }

    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}
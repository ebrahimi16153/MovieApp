package com.github.ebrahimi16153.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.ebrahimi16153.movieapp.R
import com.github.ebrahimi16153.movieapp.databinding.ActivityMainBinding

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

                if (destination.id == R.id.splashFragment2) {
                    bottomNav.visibility = View.GONE
                } else {
                    bottomNav.visibility = View.VISIBLE
                }


            }

        }

    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}
package com.onesimo.nyathi.hogwarts

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label
            supportActionBar?.let {
                if (destination.id == R.id.homeFragment) {
                    it.setHomeButtonEnabled(false)
                    it.setDisplayHomeAsUpEnabled(false)
                    it.setDisplayShowHomeEnabled(false)
                    it.elevation = 0F

                } else {
                    it.setDisplayHomeAsUpEnabled(true)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController.popBackStack()
        return true
    }
}
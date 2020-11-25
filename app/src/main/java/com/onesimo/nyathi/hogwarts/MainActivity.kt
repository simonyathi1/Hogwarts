package com.onesimo.nyathi.hogwarts

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.onesimo.nyathi.hogwarts.ui.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val arrow = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
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
                    //it.setHomeAsUpIndicator(arrow)
                    //isProfileIconSet = false
                    it.setDisplayHomeAsUpEnabled(true)
                }
            }
        }
//        observeViewModel()
//        viewModel.getCharacters()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController.popBackStack()
        return true
    }
}
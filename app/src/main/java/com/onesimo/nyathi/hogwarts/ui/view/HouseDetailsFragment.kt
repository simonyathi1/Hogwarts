package com.onesimo.nyathi.hogwarts.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.onesimo.nyathi.hogwarts.R
import com.onesimo.nyathi.hogwarts.data.House
import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import com.onesimo.nyathi.hogwarts.ui.viewmodel.CharactersViewModel
import com.onesimo.nyathi.hogwarts.ui.viewmodel.HogwartsViewModel
import com.onesimo.nyathi.hogwarts.ui.viewmodel.HousesViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.android.synthetic.main.fragment_houses.*
import timber.log.Timber

class HouseDetailsFragment : Fragment() {

    private val viewModel: HousesViewModel by activityViewModels()
    private val hogwartsViewModel: HogwartsViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_houses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        getData()
    }

    private fun getData() {
        if (hogwartsViewModel.houses.isNullOrEmpty()) {
            viewModel.getHouses()
            Timber.d("===Remote")
        } else {
            displayHouses(hogwartsViewModel.houses!!)
            Timber.d("===Local")
        }
    }

    private fun displayHouses(houses: List<House>) {
        var s = ""
        houses.forEach {
            s = s + it.name + "\n"
        }
//        houses_list.text = s
    }

    private fun observeViewModel() {
        viewModel.houses.observe(viewLifecycleOwner, Observer { houses ->
            hogwartsViewModel.houses = houses
            displayHouses(houses)
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {

        })

        viewModel.error.observe(viewLifecycleOwner, Observer {

        })
    }
}
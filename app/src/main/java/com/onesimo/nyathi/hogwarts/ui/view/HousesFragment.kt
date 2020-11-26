package com.onesimo.nyathi.hogwarts.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.onesimo.nyathi.hogwarts.R
import com.onesimo.nyathi.hogwarts.data.House
import com.onesimo.nyathi.hogwarts.ui.viewmodel.HogwartsViewModel
import com.onesimo.nyathi.hogwarts.ui.viewmodel.HousesViewModel
import kotlinx.android.synthetic.main.error_screen.*
import kotlinx.android.synthetic.main.fragment_houses.*

class HousesFragment : Fragment() {

    private val viewModel: HousesViewModel by activityViewModels()
    private val hogwartsViewModel: HogwartsViewModel by activityViewModels()
    private lateinit var houses: List<House>

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
        setupListeners()
        getData()
    }

    private fun getData() {
        if (hogwartsViewModel.houses.isNullOrEmpty()) {
            viewModel.getHouses()
        } else {
            displayHouses(hogwartsViewModel.houses!!)
        }
    }

    private fun setupListeners() {
        val action = HousesFragmentDirections.actionHousesFragmentToHouseDetailsFragment()
        gryffindor_card.setOnClickListener {
            hogwartsViewModel.selectedHouse = houses.first {
                it.name[0] == 'G'
            }
            findNavController().navigate(action)
        }
        ravenclaw_card.setOnClickListener {
            hogwartsViewModel.selectedHouse = houses.first {
                it.name[0] == 'R'
            }
            findNavController().navigate(action)
        }
        hufflepuff_card.setOnClickListener {
            hogwartsViewModel.selectedHouse = houses.first {
                it.name[0] == 'H'
            }
            findNavController().navigate(action)
        }
        slytherin_card.setOnClickListener {
            hogwartsViewModel.selectedHouse = houses.first {
                it.name[0] == 'S'
            }
            findNavController().navigate(action)
        }

        error_retry_button.setOnClickListener {
            getData()
        }
    }

    private fun displayHouses(houses: List<House>) {
        this.houses = houses
        houses.forEach {
            when {
                it.name[0] == 'G' -> gryffindor_card.seHouseDetails(
                    hogwartsViewModel.getHousesCardDetails(it)
                )
                it.name[0] == 'R' -> ravenclaw_card.seHouseDetails(
                    hogwartsViewModel.getHousesCardDetails(it)
                )
                it.name[0] == 'H' -> hufflepuff_card.seHouseDetails(
                    hogwartsViewModel.getHousesCardDetails(it)
                )
                else -> slytherin_card.seHouseDetails(
                    hogwartsViewModel.getHousesCardDetails(it)
                )
            }
        }
    }

    private fun observeViewModel() {
        viewModel.houses.observe(viewLifecycleOwner, Observer { houses ->
            hogwartsViewModel.houses = houses
            displayHouses(houses)
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                loading_indicator.visibility = View.VISIBLE
                error_screen.visibility = View.GONE
            } else {
                loading_indicator.visibility = View.GONE
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                error_screen.visibility = View.VISIBLE
                error_text.text = it
            } else {
                error_screen.visibility = View.GONE
            }
        })
    }
}
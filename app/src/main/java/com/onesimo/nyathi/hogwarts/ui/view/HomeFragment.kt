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
import com.onesimo.nyathi.hogwarts.ui.viewmodel.SortingHatViewModel
import kotlinx.android.synthetic.main.error_screen.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val viewModel: SortingHatViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        observeViewModel()
        viewModel.getSorted()
    }

    private fun setupListeners() {
        houses_btn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToHousesFragment()
            findNavController().navigate(action)
        }

        characters_btn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCharactersFragment()
            findNavController().navigate(action)
        }

        spells_btn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSpellsFragment()
            findNavController().navigate(action)
        }
        error_retry_button.setOnClickListener {
            viewModel.getSorted()
        }
    }

    private fun observeViewModel() {
        viewModel.sortingOutcome.observe(viewLifecycleOwner, Observer {
            sorting_outcome.text = it
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
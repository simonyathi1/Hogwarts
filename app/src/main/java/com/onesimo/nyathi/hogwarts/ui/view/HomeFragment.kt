package com.onesimo.nyathi.hogwarts.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.onesimo.nyathi.hogwarts.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

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
    }
}
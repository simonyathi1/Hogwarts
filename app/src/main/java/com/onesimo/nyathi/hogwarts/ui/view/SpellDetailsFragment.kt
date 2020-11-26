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
import com.onesimo.nyathi.hogwarts.data.Spell
import com.onesimo.nyathi.hogwarts.ui.viewmodel.HogwartsViewModel
import com.onesimo.nyathi.hogwarts.ui.viewmodel.SpellsViewModel
import timber.log.Timber

class SpellDetailsFragment : Fragment() {

    private val viewModel: SpellsViewModel by activityViewModels()
    private val hogwartsViewModel: HogwartsViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_spells, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        getData()
    }

    private fun getData() {
        if (hogwartsViewModel.spells.isNullOrEmpty()) {
            viewModel.getSpells()
            Timber.d("===Remote")
        } else {
            displaySpells(hogwartsViewModel.spells!!)
            Timber.d("===Local")
        }
    }

    private fun displaySpells(spells: List<Spell>) {
        var s = ""
    }

    private fun observeViewModel() {
        viewModel.spells.observe(viewLifecycleOwner, Observer { spells ->
            hogwartsViewModel.spells = spells
            displaySpells(spells)
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {

        })

        viewModel.error.observe(viewLifecycleOwner, Observer {

        })
    }
}
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
import com.onesimo.nyathi.hogwarts.data.Spell
import com.onesimo.nyathi.hogwarts.ui.view.adapter.SpellsAdapter
import com.onesimo.nyathi.hogwarts.ui.viewmodel.HogwartsViewModel
import com.onesimo.nyathi.hogwarts.ui.viewmodel.SpellsViewModel
import kotlinx.android.synthetic.main.error_screen.*
import kotlinx.android.synthetic.main.fragment_spells.*

class SpellsFragment : Fragment() {

    private val viewModel: SpellsViewModel by activityViewModels()
    private val hogwartsViewModel: HogwartsViewModel by activityViewModels()
    private lateinit var adapter: SpellsAdapter

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
        setupListeners()
        getData()
    }

    private fun getData() {
        if (hogwartsViewModel.spells.isNullOrEmpty()) {
            viewModel.getSpells()
        } else {
            displaySpells(hogwartsViewModel.spells!!)
        }
    }

    private fun displaySpells(spells: List<Spell>) {
        setAdapter(spells)
    }

    private fun setupListeners() {
        error_retry_button.setOnClickListener {
            getData()
        }
    }

    private fun observeViewModel() {
        viewModel.spells.observe(viewLifecycleOwner, Observer { spells ->
            hogwartsViewModel.spells = spells
            displaySpells(spells)
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

    private fun setAdapter(spells: List<Spell>) {
        adapter = SpellsAdapter(spells) {
            hogwartsViewModel.selectedSpell = it
            val action =
                SpellsFragmentDirections.actionSpellsFragmentToSpellDetailsFragment()
            findNavController().navigate(action)
        }
        spells_list.adapter = adapter
    }
}
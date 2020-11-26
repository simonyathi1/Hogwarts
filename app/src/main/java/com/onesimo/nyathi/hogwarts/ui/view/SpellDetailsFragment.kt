package com.onesimo.nyathi.hogwarts.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.onesimo.nyathi.hogwarts.R
import com.onesimo.nyathi.hogwarts.data.Spell
import com.onesimo.nyathi.hogwarts.ui.viewmodel.HogwartsViewModel
import kotlinx.android.synthetic.main.fragment_spell_details.*

class SpellDetailsFragment : Fragment() {

    private val hogwartsViewModel: HogwartsViewModel by activityViewModels()
    private lateinit var spell: Spell

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_spell_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
    }

    private fun getData() {
        hogwartsViewModel.selectedSpell?.let { spell = it }
        displaySpellDetails()
    }

    private fun displaySpellDetails() {
        spell.apply {
            spell_name.setInfo(name)
            is_unforgivable.setInfo(unforgivable.toString().capitalize())
            spell_use.setInfo(use)
            spell_details.setInfo(details)
        }
    }
}
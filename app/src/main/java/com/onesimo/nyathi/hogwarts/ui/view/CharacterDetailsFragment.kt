package com.onesimo.nyathi.hogwarts.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.onesimo.nyathi.hogwarts.R
import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import com.onesimo.nyathi.hogwarts.ui.viewmodel.HogwartsViewModel
import com.onesimo.nyathi.hogwarts.util.setCircularPictureFromUrl
import kotlinx.android.synthetic.main.fragment_character_details.*

class CharacterDetailsFragment : Fragment() {

    private val hogwartsViewModel: HogwartsViewModel by activityViewModels()
    private lateinit var character: MovieCharacter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
    }

    private fun getData() {
        hogwartsViewModel.selectedCharacter?.let { character = it }
        displayCharacterDetails()
    }

    private fun displayCharacterDetails() {
        character.apply {
            setCircularPictureFromUrl(imageUrl, character_details_image, requireContext())
            character_name.text = name
            character_alias.setDetails(alias)
            character_role.setDetails(role)
            character_blood_status.setDetails(bloodStatus)
            character_school.setDetails(school)
            character_house.setDetails(house)
            character_wand.setDetails(wand)
            character_ministry.setDetails(ministryOfMagic.toString().capitalize())
            character_order.setDetails(orderOfThePhoenix.toString().capitalize())
            character_army.setDetails(dumbledoresArmy.toString().capitalize())
            character_death_eater.setDetails(deathEater.toString().capitalize())
            character_boggart.setDetails(boggart)
            character_species.setDetails(species)
        }
    }
}
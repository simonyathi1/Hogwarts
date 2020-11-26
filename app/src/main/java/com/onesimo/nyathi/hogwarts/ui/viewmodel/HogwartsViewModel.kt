package com.onesimo.nyathi.hogwarts.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.onesimo.nyathi.hogwarts.R
import com.onesimo.nyathi.hogwarts.data.House
import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import com.onesimo.nyathi.hogwarts.data.Spell
import com.onesimo.nyathi.hogwarts.ui.view.customwidget.HouseItemWidget

class HogwartsViewModel :
    ViewModel() {

    var characters: List<MovieCharacter>? = null
    var houses: List<House>? = null
    var spells: List<Spell>? = null
    var selectedCharacter: MovieCharacter? = null
    var selectedHouse: House? = null
    var selectedSpell: Spell? = null
    var isDisplayingHouseCharacters = false

    fun getHousesCardDetails(house: House): HouseItemWidget.HouseDetails {
        return house.let {
            when {
                it.name[0] == 'G' ->
                    HouseItemWidget.HouseDetails(
                        it.name,
                        it.emblem,
                        R.drawable.background_gryffindor
                    )
                it.name[0] == 'R' ->
                    HouseItemWidget.HouseDetails(
                        it.name,
                        it.emblem,
                        R.drawable.background_ravenclaw
                    )
                it.name[0] == 'H' ->
                    HouseItemWidget.HouseDetails(
                        it.name,
                        it.emblem,
                        R.drawable.background_hufflepuff
                    )
                else ->
                    HouseItemWidget.HouseDetails(
                        it.name,
                        it.emblem,
                        R.drawable.background_slytherin
                    )
            }
        }
    }
}
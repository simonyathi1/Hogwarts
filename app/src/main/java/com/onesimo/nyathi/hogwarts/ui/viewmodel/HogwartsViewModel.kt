package com.onesimo.nyathi.hogwarts.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onesimo.nyathi.hogwarts.data.House
import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import com.onesimo.nyathi.hogwarts.data.Spell
import kotlinx.coroutines.launch

class HogwartsViewModel :
    ViewModel() {

    var characters: List<MovieCharacter>?=null
    var houses: List<House>?=null
    var spells: List<Spell>?=null
    var selectedCharacter: MovieCharacter?=null
    var selectedHouse: House?=null
    var selectedSpell: Spell?=null
}
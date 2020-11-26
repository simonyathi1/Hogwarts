package com.onesimo.nyathi.hogwarts.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onesimo.nyathi.hogwarts.data.Spell
import com.onesimo.nyathi.hogwarts.repo.SpellRepository
import kotlinx.coroutines.launch

class SpellsViewModel @ViewModelInject constructor(private val spellRepository: SpellRepository) :
    ViewModel() {

    var spells = MutableLiveData<List<Spell>>()
    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun getSpells() {
        viewModelScope.launch {
            loading.postValue(true)
            try {
                spellRepository.getSpells().let {
                    if (it.isSuccessful) {
                        spells.postValue(it.body())
                    } else error.postValue(it.errorBody().toString() + it.raw().body)
                }
            } catch (e: Exception) {
                error.postValue("Something went wrong. Please try again")
            }
            loading.postValue(false)
        }
    }
}
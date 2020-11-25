package com.onesimo.nyathi.hogwarts.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import com.onesimo.nyathi.hogwarts.repo.CharacterRepository
import kotlinx.coroutines.launch

class CharactersViewModel @ViewModelInject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {

    var characters = MutableLiveData<List<MovieCharacter>>()
    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun getCharacters() {
        viewModelScope.launch {
            loading.postValue(true)
            characterRepository.getCharacters().let {
                if (it.isSuccessful) {
                    characters.postValue(it.body())
                } else error.postValue(it.errorBody().toString() + it.raw().body)
            }
            loading.postValue(false)
        }
    }
}
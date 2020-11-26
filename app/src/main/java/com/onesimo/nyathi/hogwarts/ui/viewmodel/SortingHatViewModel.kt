package com.onesimo.nyathi.hogwarts.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onesimo.nyathi.hogwarts.repo.SortingHatRepository
import kotlinx.coroutines.launch

class SortingHatViewModel @ViewModelInject constructor(private val sortingHatRepository: SortingHatRepository) :
    ViewModel() {

    var sortingOutcome = MutableLiveData<String>()
    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun getSorted() {
        viewModelScope.launch {
            loading.postValue(true)
            try {
                sortingHatRepository.getSortingHatDecision().let {
                    if (it.isSuccessful) {
                        sortingOutcome.postValue(it.body()?.house)
                    } else error.postValue(it.errorBody().toString() + it.raw().body)
                }
            } catch (e: Exception) {
                error.postValue("Something went wrong. Please try again")
            }
            loading.postValue(false)
        }
    }
}
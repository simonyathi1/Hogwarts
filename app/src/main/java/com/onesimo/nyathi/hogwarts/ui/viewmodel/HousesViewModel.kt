package com.onesimo.nyathi.hogwarts.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onesimo.nyathi.hogwarts.data.House
import com.onesimo.nyathi.hogwarts.repo.HouseRepository
import kotlinx.coroutines.launch

class HousesViewModel @ViewModelInject constructor(private val houseRepository: HouseRepository) :
    ViewModel() {

    var houses = MutableLiveData<List<House>>()
    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun getHouses() {
        viewModelScope.launch {
            loading.postValue(true)
            houseRepository.getHouses().let {
                if (it.isSuccessful) {
                    houses.postValue(it.body())
                } else error.postValue(it.errorBody().toString() + it.raw().body)
            }
            loading.postValue(false)
        }
    }
}
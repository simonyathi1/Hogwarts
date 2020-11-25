package com.onesimo.nyathi.hogwarts.repo

import com.onesimo.nyathi.hogwarts.data.House
import retrofit2.Response

interface HouseRepository {
    suspend  fun getHouses(): Response<List<House>>
}
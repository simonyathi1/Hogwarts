package com.onesimo.nyathi.hogwarts.repo.implementation

import com.onesimo.nyathi.hogwarts.repo.HouseRepository
import com.onesimo.nyathi.hogwarts.service.Service
import javax.inject.Inject

class HouseRepositoryImpI @Inject constructor(private val service: Service) :
    HouseRepository {

    override suspend fun getHouses() = service.getHouses()
}
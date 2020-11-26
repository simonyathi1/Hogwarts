package com.onesimo.nyathi.hogwarts.repo.implementation

import com.onesimo.nyathi.hogwarts.data.SortingOutcome
import com.onesimo.nyathi.hogwarts.repo.SortingHatRepository
import com.onesimo.nyathi.hogwarts.service.Service
import retrofit2.Response
import javax.inject.Inject

class SortingHatRepositoryImpl @Inject constructor(private val service: Service) :
    SortingHatRepository {
    override suspend fun getSortingHatDecision(): Response<SortingOutcome> = service.getSorted()
}
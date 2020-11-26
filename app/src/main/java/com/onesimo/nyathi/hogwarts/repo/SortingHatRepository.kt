package com.onesimo.nyathi.hogwarts.repo

import com.onesimo.nyathi.hogwarts.data.SortingOutcome
import retrofit2.Response

interface SortingHatRepository {
    suspend fun getSortingHatDecision(): Response<SortingOutcome>
}
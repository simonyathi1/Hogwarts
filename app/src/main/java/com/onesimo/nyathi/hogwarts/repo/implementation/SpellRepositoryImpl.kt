package com.onesimo.nyathi.hogwarts.repo.implementation

import com.onesimo.nyathi.hogwarts.data.Spell
import com.onesimo.nyathi.hogwarts.repo.SpellRepository
import com.onesimo.nyathi.hogwarts.service.Service
import retrofit2.Response
import javax.inject.Inject

class SpellRepositoryImpl @Inject constructor(private val service: Service) :
    SpellRepository {
    override suspend fun getSpells(): Response<List<Spell>> = service.getSpells()
}
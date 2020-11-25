package com.onesimo.nyathi.hogwarts.repo

import com.onesimo.nyathi.hogwarts.data.Spell
import retrofit2.Response

interface SpellRepository {
    suspend fun getSpells(): Response<List<Spell>>
}
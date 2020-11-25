package com.onesimo.nyathi.hogwarts.repo

import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import retrofit2.Response

interface CharacterRepository {
    suspend fun getCharacters(): Response<List<MovieCharacter>>
}
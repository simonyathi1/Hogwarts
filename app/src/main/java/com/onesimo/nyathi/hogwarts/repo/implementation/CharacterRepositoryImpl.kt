package com.onesimo.nyathi.hogwarts.repo.implementation

import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import com.onesimo.nyathi.hogwarts.repo.CharacterRepository
import com.onesimo.nyathi.hogwarts.service.Service
import retrofit2.Response
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private var service: Service) :
    CharacterRepository {
    override suspend fun getCharacters(): Response<List<MovieCharacter>> = service.getCharacters()
}
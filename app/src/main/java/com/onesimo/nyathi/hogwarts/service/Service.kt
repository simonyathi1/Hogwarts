package com.onesimo.nyathi.hogwarts.service


import com.onesimo.nyathi.hogwarts.data.House
import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import com.onesimo.nyathi.hogwarts.data.SortingOutcome
import com.onesimo.nyathi.hogwarts.data.Spell
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("characters")
    suspend fun getCharacters(): Response<List<MovieCharacter>>

    @GET("houses")
    suspend fun getHouses(): Response<List<House>>

    @GET("spells")
    suspend fun getSpells(): Response<List<Spell>>

    @GET("sortingHat")
    suspend fun getSorted(): Response<SortingOutcome>
}
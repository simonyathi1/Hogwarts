package com.onesimo.nyathi.hogwarts.service


import com.onesimo.nyathi.hogwarts.data.House
import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import com.onesimo.nyathi.hogwarts.data.Spell
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("characters")
    suspend fun getCharacters(): Response<List<MovieCharacter>>

//    @GET("characters/{character_id}")
//    suspend fun getCharacterById(@Path("character_id") accountId: String): Response<MovieCharacter>

    @GET("houses")
    suspend fun getHouses(): Response<List<House>>

//    @GET("houses/{id}")
//    suspend fun getHouse(@Path("id") houseId: String): Response<List<HouseResponse>>

    @GET("spells")
    suspend fun getSpells(): Response<List<Spell>>
}
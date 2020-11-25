package com.onesimo.nyathi.hogwarts.data

data class MovieCharacter(
    val _id: String,
    val name: String,
    val alias: String,
    val role: String,
    val imageUrl: String,
    val house: String,
    val wand: String,
    val school: String,
    val ministryOfMagic: Boolean,
    val orderOfThePhoenix: Boolean,
    val dumbledoresArmy: Boolean,
    val deathEater: Boolean,
    val bloodStatus: String,
    val boggart: String,
    val species: String
)
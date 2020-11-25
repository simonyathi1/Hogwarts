package com.onesimo.nyathi.hogwarts.data

data class Spell(
    val _id: String,
    val name: String,
    val unforgivable: Boolean,
    val classification: String,
    val use: String,
    val details: String
)
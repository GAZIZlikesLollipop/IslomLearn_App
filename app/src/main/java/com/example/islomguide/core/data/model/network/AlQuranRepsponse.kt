package com.example.islomguide.core.data.model.network

import kotlinx.serialization.Serializable

@Serializable
data class AlQuranRepsponse(
    val surah : List<Surah?>
)
@Serializable
data class Surah(
    val number : Int?,
    val name: String?,
    val englishName : String?,
    val revelationType : String?,
    val numberOfAyahs : Int?,
    val ayahs: List<Ayahs?>
)
@Serializable
data class Ayahs(
    val number: Int?,
    val text : String?,
    val numberInSurah : Int
)

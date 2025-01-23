package com.example.islomguide.core.data.model.network

import kotlinx.serialization.Serializable

@Serializable
data class AlQuranRepsponse(
    val surahsRepsponse: SurahsRepsponse,
    val juzResponse: JuzResponse
)

@Serializable
data class SurahsRepsponse(
    val data: SurahsData?
)

@Serializable
data class SurahsData(
    val number : Int?,
    val name: String?,
    val englishName : String?,
    val revelationType : String?,
    val numberOfAyahs : Int?,
    val ayahs: List<S_Ayahs>
)

@Serializable
data class S_Ayahs(
    val number: Int?,
    val text : String?,
    val numberInSurah : Int?
)

@Serializable

data class JuzResponse(
    val data : JuzData
)

@Serializable
data class JuzData(
    val number: Int?,
    val ayahs: List<J_Ayahs>,
    val surahs: Map<Int, Surahs>
)

@Serializable
data class J_Ayahs(
    val number: Int?,
    val text: String?,
    val numberInSurah : Int?
)

@Serializable
data class Surahs(
    val number: Int?,
    val englishName: String?,
    val numberOfAyahs: Int?
)

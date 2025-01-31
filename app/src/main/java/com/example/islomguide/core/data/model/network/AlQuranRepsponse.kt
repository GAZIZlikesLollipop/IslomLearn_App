package com.example.islomguide.core.data.model.network

import kotlinx.serialization.Serializable

@Serializable
data class QuranRepsponse(
    val data: QuranData
)
@Serializable
data class QuranData(
    val surahs: List<QuranSurahs?>
)
@Serializable
data class QuranSurahs(
    val number : Int?,
    val name: String?,
    val englishName : String?,
    val revelationType : String?,
    val ayahs: List<QuranAyahs>
)
@Serializable
data class QuranAyahs(
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

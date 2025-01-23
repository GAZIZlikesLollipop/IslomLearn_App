package com.example.islomguide.core.data.model.network

import kotlinx.serialization.Serializable

@Serializable
data class PrayerTimesResponse(
    val data: Data?
)

@Serializable
data class Data(
    val timings: Timings?,       // Объект timings
    val date: Date?,             // Объект date
)

@Serializable
data class Timings(
    val Fajr: String? = null,
    val Sunrise: String? = null,
    val Dhuhr: String? = null,
    val Asr: String? = null,
    val Sunset: String? = null,
    val Maghrib: String? = null,
    val Isha: String? = null,
    val Imsak: String? = null,
    val Midnight: String? = null,
    val Firstthird: String? = null,
    val Lastthird: String? = null
)

@Serializable
data class Date(
    val readable: String?,       // Читаемая дата
    val gregorian: Gregorian? = null,   // Григорианская дата12
)

@Serializable
data class Gregorian(
    val date: String?,
    val weekday: Weekday?,
    val month: Month?,
    val year: String?
)

@Serializable
data class Weekday(
    val en: String?,
    )

@Serializable
data class Month(
    val en: String?,
   )


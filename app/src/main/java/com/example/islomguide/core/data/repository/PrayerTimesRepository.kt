package com.example.islomguide.core.data.repository

import com.example.islomguide.core.data.model.network.Timings
import com.example.islomguide.core.data.network.AladhanApi

interface PrayerTimesRepository{
    suspend fun getPrayerTimes(city: String,country: String,date: String): Timings
}

class NetworkPrayerTimeRepository(private val aladhanApi: AladhanApi): PrayerTimesRepository {

    override suspend fun getPrayerTimes(city: String, country: String, date: String): Timings {
        val response = aladhanApi.getPrayerTimes(date,city,country)
        return response.data?.timings ?: Timings()
    }

}

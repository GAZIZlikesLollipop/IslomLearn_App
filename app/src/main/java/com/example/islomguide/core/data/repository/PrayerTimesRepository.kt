package com.example.islomguide.core.data.repository

import com.example.islomguide.core.data.model.Timings
import com.example.islomguide.core.data.network.PrayerTimeApi

interface PrayerTimesRepository{
    suspend fun getPrayerTimes(city: String,country: String,date: String): Timings
}

class NetworkPrayerTimeRepository(private val prayerTimeApi: PrayerTimeApi): PrayerTimesRepository {

    override suspend fun getPrayerTimes(city: String, country: String, date: String):Timings {
        val response = prayerTimeApi.getPrayerTimes(date,city,country)
        return response.data?.timings ?: Timings()
    }

}

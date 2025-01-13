package com.example.islomguide.core.data.network

import com.example.islomguide.core.data.model.PrayerTimesResponse
import com.example.islomguide.core.data.model.Timings
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PrayerTimeApi {

    @GET("v1/timingsByCity/{date}")
    suspend fun getPrayerTimes(
        @Path("date") date: String,
        @Query("city") city: String,
        @Query("country") country : String
    ): PrayerTimesResponse
}

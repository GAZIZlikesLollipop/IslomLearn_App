package com.example.islomguide.core.data.network

import com.example.islomguide.core.data.model.network.AlQuranRepsponse
import com.example.islomguide.core.data.model.network.PrayerTimesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AladhanApi {
    @GET("v1/timingsByCity/{date}")
    suspend fun getPrayerTimes(
        @Path("date") date: String,
        @Query("city") city: String,
        @Query("country") country : String
    ): PrayerTimesResponse
}
interface AlQuranApi {
    @GET("v1/surah/{number}")
    suspend fun getBookContent(@Path("number") number: Int): AlQuranRepsponse
}
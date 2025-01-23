package com.example.islomguide.core.data.network

import com.example.islomguide.core.data.model.network.JuzResponse
import com.example.islomguide.core.data.model.network.SurahsRepsponse
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
    @GET("v1/surah/{index}")
    suspend fun getSurahs(
        @Path("index") index: Int
    ): SurahsRepsponse
    @GET("v1/juz/{num}/quran-uthmani")
    suspend fun getJuz(
        @Path("num") num : Int
    ): JuzResponse
}

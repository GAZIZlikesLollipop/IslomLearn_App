package com.example.islomguide.core.data.network

import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

val json = Json {
    ignoreUnknownKeys = true // Игнорирует неизвестные поля в JSON
    isLenient = true // Разрешает гибкий синтаксис JSON
}

object RetrofitClient {
    private const val BASE_URL = "https://api.aladhan.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Указание базового URL
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())) // Конвертер для JSON
            .build()
    }

    val api: PrayerTimeApi by lazy {
        retrofit.create(PrayerTimeApi::class.java)
    }

}

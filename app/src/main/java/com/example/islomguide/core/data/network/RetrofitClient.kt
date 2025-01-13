package com.example.islomguide.core.data.network

import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType



object RetrofitClient {

    val json = Json {
        ignoreUnknownKeys = true // Игнорирует неизвестные поля в JSON
        isLenient = true // Разрешает гибкий синтаксис JSON
    }

    private const val TIME_BASE_URL = "https://api.aladhan.com/"

    private val time_retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(TIME_BASE_URL) // Указание базового URL
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())) // Конвертер для JSON
            .build()
    }

    val aladhanApi: AladhanApi by lazy {
        time_retrofit.create(AladhanApi::class.java)
    }

    private const val BOOK_BASE_URL = "http://api.alquran.cloud/"

    private val book_retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BOOK_BASE_URL) // Указание базового URL
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())) // Конвертер для JSON
            .build()
    }

    val bookApi: AlQuranApi by lazy {
        book_retrofit.create(AlQuranApi::class.java)
    }

}

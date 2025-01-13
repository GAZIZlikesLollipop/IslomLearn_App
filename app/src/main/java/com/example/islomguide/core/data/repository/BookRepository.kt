package com.example.islomguide.core.data.repository

import com.example.islomguide.core.data.model.network.Surah
import com.example.islomguide.core.data.network.AlQuranApi

interface BookRepository {
    suspend fun getBookContent(number: Int): Surah?
}

class NetworkBookRepository(private val alQuranApi : AlQuranApi): BookRepository{

    override suspend fun getBookContent(number: Int): Surah? {
       return alQuranApi.getBookContent(number).surah[number]
    }

}
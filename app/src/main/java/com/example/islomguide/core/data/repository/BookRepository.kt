package com.example.islomguide.core.data.repository

import com.example.islomguide.core.data.model.network.JuzData
import com.example.islomguide.core.data.model.network.QuranData
import com.example.islomguide.core.data.network.AlQuranApi

interface BookRepository {
    suspend fun getJuz(juzId : Int): JuzData?
    suspend fun getQuranContent(): QuranData?
}

class NetworkBookRepository(private val alQuranApi : AlQuranApi): BookRepository{

    override suspend fun getJuz(juzId: Int): JuzData {
        return alQuranApi.getJuz(juzId).data
    }

    override suspend fun getQuranContent(): QuranData {
        return alQuranApi.getQuranContent().data
    }

}

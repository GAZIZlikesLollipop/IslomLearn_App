package com.example.islomguide.core.data.repository

import com.example.islomguide.core.data.model.network.JuzData
import com.example.islomguide.core.data.model.network.SurahsData
import com.example.islomguide.core.data.network.AlQuranApi

interface BookRepository {
    suspend fun getSurahs(surahId: Int): SurahsData?
    suspend fun getJuz(juzId : Int): JuzData?
}

class NetworkBookRepository(private val alQuranApi : AlQuranApi): BookRepository{

    override suspend fun getSurahs(surahId: Int): SurahsData? {
        return alQuranApi.getSurahs(surahId).data
    }

    override suspend fun getJuz(juzId: Int): JuzData? {
        return alQuranApi.getJuz(juzId).data
    }

}

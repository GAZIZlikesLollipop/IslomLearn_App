package com.example.islomguide.core.main

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.islomguide.core.data.repository.UserLocationRepository

private const val LOCATiON = "location"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(LOCATiON)

class IslomLearnApplication : Application() {
    lateinit var userLocationRepository: UserLocationRepository
    override fun onCreate() {
        super.onCreate()
        userLocationRepository = UserLocationRepository(dataStore)

    }
}

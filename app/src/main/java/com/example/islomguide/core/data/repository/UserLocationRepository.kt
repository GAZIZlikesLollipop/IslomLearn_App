package com.example.islomguide.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserLocationExm {
    suspend fun saveLocationPreferences(city: String, country: String)
    val getLocation: Flow<Pair<String, String>>
}

class UserLocationRepository(
    private val dataStore: DataStore<Preferences>
): UserLocationExm{
    private companion object{
        val SELECTED_CITY = stringPreferencesKey("selected_city")
        val SELECTED_COUNTRY = stringPreferencesKey("seleted_country")
    }

    override suspend fun saveLocationPreferences(city: String, country: String){
        dataStore.edit { preferences ->
            preferences[SELECTED_CITY] = city
            preferences[SELECTED_COUNTRY] = country
        }
    }

    override val getLocation : Flow<Pair<String, String>> = dataStore.data
        .map { preferences ->
            Pair(
                preferences[SELECTED_CITY] ?: "",
                preferences[SELECTED_COUNTRY] ?: ""
            )
        }
}

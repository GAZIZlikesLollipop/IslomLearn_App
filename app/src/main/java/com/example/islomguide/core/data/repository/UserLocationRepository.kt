package com.example.islomguide.core.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserLocationRepository(
    private val dataStore: DataStore<Preferences>
){

    private companion object{
        val SELECTED_CITY = stringPreferencesKey("selected_city")
        val SELECTED_COUNTRY = stringPreferencesKey("seleted_country")
        const val TAG = "UserPreferencesRepo"
    }

    suspend fun saveCityPreferences(city: String){
        dataStore.edit { preferences ->
            preferences[SELECTED_CITY] = city
        }
    }

    suspend fun saveCountryPreferences(country: String){
        dataStore.edit { preferences ->
            preferences[SELECTED_COUNTRY] = country
        }
    }

    val getCity: Flow<String> = dataStore.data
        .catch{
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[SELECTED_CITY] ?: ""
        }
    val getCountry : Flow<String> = dataStore.data
        .catch{
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[SELECTED_COUNTRY] ?: ""
        }
    val getLocation : Flow<String> = dataStore.data
        .catch{
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[SELECTED_CITY] ?: ""
            preferences[SELECTED_COUNTRY] ?: ""
        }
}

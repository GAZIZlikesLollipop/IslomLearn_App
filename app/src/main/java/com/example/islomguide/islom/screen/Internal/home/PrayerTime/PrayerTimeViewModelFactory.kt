package com.example.islomguide.islom.screen.Internal.home.PrayerTime

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.islomguide.core.data.repository.PrayerTimesRepository
import com.example.islomguide.core.data.repository.UserLocationRepository
import com.example.islomguide.core.main.IslomLearnApplication

class PrayerTimeViewModelFactory(
    private val prayerTimesRepository: PrayerTimesRepository,
    private val userLocationRepository: UserLocationRepository // Передаем UserLocationRepository напрямую
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrayerTimeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PrayerTimeViewModel(prayerTimesRepository, userLocationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
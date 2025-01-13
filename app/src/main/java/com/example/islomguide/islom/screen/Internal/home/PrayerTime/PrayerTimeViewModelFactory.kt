package com.example.islomguide.islom.screen.Internal.home.PrayerTime

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.islomguide.core.data.repository.PrayerTimesRepository
import com.example.islomguide.core.data.repository.UserLocationRepository

class PrayerTimeViewModelFactory(
    private val prayerTimesRepository: PrayerTimesRepository,
    private val userLocationRepository: UserLocationRepository // Передаем UserLocationRepository напрямую
) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.O)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrayerTimeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PrayerTimeViewModel(prayerTimesRepository, userLocationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
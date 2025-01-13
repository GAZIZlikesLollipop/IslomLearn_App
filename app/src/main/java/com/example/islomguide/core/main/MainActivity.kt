package com.example.islomguide.core.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.compose.IslomGuideTheme
import com.example.islomguide.core.data.network.RetrofitClient
import com.example.islomguide.core.data.repository.NetworkPrayerTimeRepository
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val prayerTimesRepository = NetworkPrayerTimeRepository(RetrofitClient.api)
        val application = application as IslomLearnApplication // Get the application instance
        val userLocationRepository = application.userLocationRepository // Access the repository
        val viewModelFactory = PrayerTimeViewModelFactory(prayerTimesRepository, userLocationRepository)

        val prayerTimeViewModel = ViewModelProvider(this, viewModelFactory).get(PrayerTimeViewModel::class.java)
        setContent {
            IslomGuideTheme {
                Navigation()
            }

        }
    }
}

package com.example.islomguide.core.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.compose.IslomGuideTheme
import com.example.islomguide.core.data.network.RetrofitClient
import com.example.islomguide.core.data.repository.NetworkBookRepository
import com.example.islomguide.core.data.repository.NetworkPrayerTimeRepository
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModelFactory
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val prayerTimesRepository = NetworkPrayerTimeRepository(RetrofitClient.aladhanApi)
        val application = application as IslomLearnApplication // Get the application instance
        val userLocationRepository = application.userLocationRepository // Access the repository
        val prayerTimeVMFactory = PrayerTimeViewModelFactory(prayerTimesRepository, userLocationRepository)

        val prayerTimeViewModel = ViewModelProvider(this, prayerTimeVMFactory).get(PrayerTimeViewModel::class.java)

        val bookRepository = NetworkBookRepository(RetrofitClient.bookApi)
        val bookVMFactory = BookViewModelFactory(bookRepository)

        val bookViewModel = ViewModelProvider(this, bookVMFactory).get(BookViewModel::class.java)
        setContent {
            IslomGuideTheme {
                Navigation()
            }

        }
    }
}

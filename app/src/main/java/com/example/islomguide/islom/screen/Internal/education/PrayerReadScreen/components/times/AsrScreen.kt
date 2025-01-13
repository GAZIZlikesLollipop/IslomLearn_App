package com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components.times

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.PrayerReadVM
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components.TimesScreen

@Composable
fun AsrScreen(
    navController: NavController,
    viewModel: PrayerReadVM
){
    TimesScreen(navController,viewModel)
}
package com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components.times

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.PrayerReadVM
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components.TimesScreen

@Composable
fun ZuhrScreen(
    navController: NavController,
    viewModel: PrayerReadVM
){

    TimesScreen(
        navController,
        viewModel
    )
}
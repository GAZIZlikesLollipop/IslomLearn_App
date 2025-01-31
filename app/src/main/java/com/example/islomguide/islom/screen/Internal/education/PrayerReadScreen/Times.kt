package com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components.TimesScreen

@Composable
fun FajrScreen(
    navController: NavController,
    viewModel: PrayerReadVM
){
    TimesScreen(navController, viewModel)
}
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

@Composable
fun MagribScreen(
    navController: NavController,
    viewModel: PrayerReadVM
){
    TimesScreen(navController,viewModel)
}
@Composable
fun AsrScreen(
    navController: NavController,
    viewModel: PrayerReadVM
){
    TimesScreen(navController,viewModel)
}
@Composable
fun IshaScreen(
    navController: NavController,
    viewModel: PrayerReadVM
){
    TimesScreen(navController,viewModel)
}
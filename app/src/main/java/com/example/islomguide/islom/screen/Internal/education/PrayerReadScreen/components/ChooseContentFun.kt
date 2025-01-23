package com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components

import androidx.navigation.NavController
import com.example.islomguide.core.main.Routes.FeatureRoutes

fun chooseContent(
    navController: NavController,
    FajrContent : String,
    ZuhrContent : String,
    AsrContent : String,
    MagribContent : String,
    IshaContent : String
): String{
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    return when(currentRoute){
        FeatureRoutes.PR_Fajr.route -> FajrContent
        FeatureRoutes.PR_Zuhr.route -> ZuhrContent
        FeatureRoutes.PR_Asr.route -> AsrContent
        FeatureRoutes.PR_Magrib.route -> MagribContent
        FeatureRoutes.PR_Isha.route -> IshaContent
        else -> ""
    }
}
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
        FeatureRoutes.PR_Fajr.name -> FajrContent
        FeatureRoutes.PR_Zuhr.name -> ZuhrContent
        FeatureRoutes.PR_Asr.name -> AsrContent
        FeatureRoutes.PR_Magrib.name -> MagribContent
        FeatureRoutes.PR_Isha.name -> IshaContent
        else -> ""
    }
}
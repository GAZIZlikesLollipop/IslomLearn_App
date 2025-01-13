package com.example.islomguide.core.main

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.islomguide.core.main.Routes.BaseGraph
import com.example.islomguide.core.main.Routes.FeatureRoutes
import com.example.islomguide.core.main.Routes.InternalGraph
import com.example.islomguide.islom.logic.IslomViewModel
import com.example.islomguide.islom.screen.Internal.CalendarScreen
import com.example.islomguide.islom.screen.Internal.DuaScreen
import com.example.islomguide.islom.screen.Internal.MosqueScreen
import com.example.islomguide.islom.screen.Internal.PrayerReadScreen
import com.example.islomguide.islom.screen.Internal.PrayerTimeScreen
import com.example.islomguide.islom.screen.Internal.PrayerTrackerScreen
import com.example.islomguide.islom.screen.Internal.QiblaLocScreen
import com.example.islomguide.islom.screen.Internal.TasbexScreen
import com.example.islomguide.islom.screen.Internal.education.IslomBaseGuideScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.PrayerReadVM
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components.times.AsrScreen
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components.times.FajrScreen
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components.times.IshaScreen
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components.times.MagribScreen
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components.times.ZuhrScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections.Bookmarks
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections.Juz
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel
import com.example.islomguide.islom.screen.Internal.practices.TasbexScreen.TasbexViewModel
import com.example.islomguide.islom.screen.Internal.practices.ZicryScreen
import com.example.islomguide.islom.ui.screen.educationScreen.Edu
import com.example.islomguide.islom.ui.screen.homeScreen.Home
import com.example.islomguide.islom.ui.screen.practicesScreen.Practices
import com.example.islomguide.islom.ui.screen.settingScreen.Settings

@SuppressLint("NewApi")
@Composable
fun Navigation() {

    val viewModel: IslomViewModel = viewModel()
    val navController: NavHostController = rememberNavController()
    val tasbexViewModel : TasbexViewModel = viewModel()
    val prayerReadVM : PrayerReadVM = viewModel()
    val prayerTimeVM : PrayerTimeViewModel = viewModel()
    val bookViewModel : BookViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = BaseGraph.Home.name
    ) {
        composable(BaseGraph.Home.name) {
            Home(
                viewModel = prayerTimeVM,
                navController = navController ,
            )
        }
        composable(BaseGraph.Education.name) {
            Edu(
                viewModel = viewModel,
                navController = navController,
            )
        }
        composable(BaseGraph.Practice.name) {
            Practices(
                viewModel = viewModel,
                navController = navController,
            )

        }
        composable(BaseGraph.Setting.name) {
            Settings(
                PTViewModel = prayerTimeVM,
                navController = navController
            )
        }
// Internal Navigation
        composable(InternalGraph.Book.name){
            BookScreen(
                viewModel = bookViewModel,
                navController = navController,

            )
        }
        composable(InternalGraph.Mosque.name){
            MosqueScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }
        composable(InternalGraph.Tasbeh.name){
            TasbexScreen(
                viewModel = tasbexViewModel,
                navController = navController,
            )
        }
        composable(InternalGraph.Prayer_Time.name){
            PrayerTimeScreen(
                viewModel = prayerTimeVM,
                navController = navController
            )
        }
        composable(InternalGraph.Qibla_Location.name){
            QiblaLocScreen(
                viewModel = viewModel,
                navController = navController,

            )
        }
        composable(InternalGraph.Calendar.name){
            CalendarScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }
        composable(InternalGraph.Prayer_Read.name){
            PrayerReadScreen(
                viewModel = prayerReadVM,
                navController = navController,
                viewModel2 = prayerTimeVM
            )
        }
        composable(InternalGraph.Islom_Base_Guide.name){
            IslomBaseGuideScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }
        composable(InternalGraph.Zicry.name){
            ZicryScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }
        composable(InternalGraph.Prayer_Tracker.name){
            PrayerTrackerScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }
        composable(InternalGraph.Dua.name) {
            DuaScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(FeatureRoutes.PR_Fajr.name) {
            FajrScreen(
                navController,
                prayerReadVM
            )
        }
        composable(FeatureRoutes.PR_Zuhr.name) {
            ZuhrScreen(
                navController,
                prayerReadVM
            )
        }
        composable(FeatureRoutes.PR_Asr.name) {
            AsrScreen(
                navController,
                prayerReadVM
            )
        }
        composable(FeatureRoutes.PR_Magrib.name) {
            MagribScreen(
                navController,
                prayerReadVM
            )
        }
        composable(FeatureRoutes.PR_Isha.name) {
           IshaScreen(
                navController,
                prayerReadVM
            )
        }
        composable(FeatureRoutes.B_Juz.name) {
            Juz(
                navController,
                bookViewModel
            )
        }
        composable(FeatureRoutes.B_Bookmarks.name) {
            Bookmarks(
                navController,
                bookViewModel
            )
        }
    }
}

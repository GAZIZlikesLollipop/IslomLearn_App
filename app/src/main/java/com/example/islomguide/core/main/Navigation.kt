package com.example.islomguide.core.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.islomguide.core.data.repository.BookRepository
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
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections.BookScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModelFactory
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.internal.BookDetail
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.internal.JuzDetail
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
fun Navigation(bookRepository: BookRepository) {

    val navController: NavHostController = rememberNavController()
    val prayerReadVM : PrayerReadVM = viewModel()
    val prayerTimeVM : PrayerTimeViewModel = viewModel()
    val bookViewModel : BookViewModel = viewModel(
        factory = BookViewModelFactory(bookRepository)
    )
    val tasbexViewModel : TasbexViewModel = viewModel()
    val viewModel : IslomViewModel = viewModel()
    val currentRoute = navController.currentDestination?.route
    NavHost(
        navController = navController,
        startDestination = BaseGraph.Home.route
    ) {

        composable(
            BaseGraph.Home.route,
//            enterTransition = {
//                slideIntoContainer(
//                    towards = AnimatedContentTransitionScope.SlideDirection.End,
//                    animationSpec = tween(700)
//                )
//            }
        ) {
            Home(
                viewModel = prayerTimeVM,
                navController = navController ,
            )
        }
        composable(BaseGraph.Education.route,
//            enterTransition = {
//            slideIntoContainer(
//                towards = AnimatedContentTransitionScope.SlideDirection.Right,
//                animationSpec = tween(700)
//            )
//        }
        ) {
            Edu(
                viewModel = viewModel,
                navController = navController,
            )
        }
        composable(BaseGraph.Practice.route) {
            Practices(
                viewModel = viewModel,
                navController = navController,
            )

        }
        composable(BaseGraph.Setting.route) {
            Settings(
                PTViewModel = prayerTimeVM,
                navController = navController
            )
        }



// Internal Navigation
        composable(InternalGraph.Mosque.route){
            MosqueScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(InternalGraph.PrayerTime.route){
            PrayerTimeScreen(
                viewModel = prayerTimeVM,
                navController = navController
            )
        }

        composable(InternalGraph.QiblaLocation.route){
            QiblaLocScreen(
                viewModel = viewModel,
                navController = navController,

                )
        }

        composable(InternalGraph.Calendar.route){
            CalendarScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(InternalGraph.PrayerTracker.route){
            PrayerTrackerScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(InternalGraph.Book.route){
            BookScreen(
                viewModel = bookViewModel,
                navController = navController,
            )
        }

        composable(InternalGraph.PrayerRead.route){
            PrayerReadScreen(
                viewModel = prayerReadVM,
                navController = navController,
                viewModel2 = prayerTimeVM
            )
        }

        composable(InternalGraph.IslamBaseGuide.route){
            IslomBaseGuideScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }



        composable(InternalGraph.Tasbeh.route){

            TasbexScreen(
                viewModel = tasbexViewModel,
                navController = navController,
            )
        }
        composable(InternalGraph.Zicry.route){
            ZicryScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(InternalGraph.Dua.route) {
            DuaScreen(
                viewModel = viewModel,
                navController = navController
            )
        }


        composable(FeatureRoutes.PR_Fajr.route) {
            FajrScreen(
                navController,
                prayerReadVM
            )
        }

        composable(
            FeatureRoutes.PR_Zuhr.route,

            ) {
            ZuhrScreen(
                navController,
                prayerReadVM
            )
        }

        composable(FeatureRoutes.PR_Asr.route) {
            AsrScreen(
                navController,
                prayerReadVM
            )
        }

        composable(FeatureRoutes.PR_Magrib.route) {
            MagribScreen(
                navController,
                prayerReadVM
            )
        }

        composable(FeatureRoutes.PR_Isha.route) {
            IshaScreen(
                navController,
                prayerReadVM
            )
        }

        composable(FeatureRoutes.B_Juz.route) {
            Juz(
                navController,
                bookViewModel
            )
        }

        composable(FeatureRoutes.B_Bookmarks.route) {
            Bookmarks(
                navController,
                bookViewModel
            )
        }

        composable(
            "b_book_dt/{suraId}/{name}",
            arguments = listOf(
                navArgument("suraId") { type = NavType.IntType },
                navArgument("name") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val suraId = backStackEntry.arguments?.getInt("suraId") ?: 0
            val name = backStackEntry.arguments?.getString("name") ?: "" // Значение по умолчанию

            BookDetail(
                navController = navController,
                viewModel = bookViewModel,
                surahId = suraId,
                name = name
            )
        }

        composable(
            "b_juz_dt/{juzId}/{surId}",
            arguments = listOf(
                navArgument("juzId") { type = NavType.IntType },
                navArgument("surId") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val juzId = backStackEntry.arguments?.getInt("juzId") ?: 0
            val surId = backStackEntry.arguments?.getInt("surId") ?: 0

            JuzDetail(
                navController = navController,
                viewModel = bookViewModel,
                juzId = juzId,
                surId = surId
            )
        }

    }
}

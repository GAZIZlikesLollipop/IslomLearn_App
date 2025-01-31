package com.example.islomguide.core.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
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
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections.BookScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.internal.BookDetail
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.internal.JuzDetail
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.PrayerReadVM
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.AsrScreen
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.FajrScreen
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.IshaScreen
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.MagribScreen
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.ZuhrScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections.Bookmarks
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections.Juz
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel
import com.example.islomguide.islom.screen.Internal.practices.TasbexScreen.TasbexViewModel
import com.example.islomguide.islom.screen.Internal.practices.ZicryScreen
import com.example.islomguide.islom.ui.screen.educationScreen.Edu
import com.example.islomguide.islom.ui.screen.homeScreen.Home
import com.example.islomguide.islom.ui.screen.homeScreen.WelcomeScreen
import com.example.islomguide.islom.ui.screen.practicesScreen.Practices
import com.example.islomguide.islom.ui.screen.settingScreen.Settings

@SuppressLint("NewApi")
@Composable
fun Navigation(navController : NavHostController) {


    val prayerReadVM : PrayerReadVM = viewModel()
    val prayerTimeVM : PrayerTimeViewModel = viewModel()
    val bookViewModel : BookViewModel = viewModel()
    val tasbexViewModel : TasbexViewModel = viewModel()
    val viewModel : IslomViewModel = viewModel()
    val first_Route = if(prayerTimeVM.selectedCountry.isNotEmpty() && prayerTimeVM.selectedCity.isNotEmpty()){
        BaseGraph.Home.route
    }else{
        BaseGraph.Welcome.route
    }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavHost(
        navController = navController,
        startDestination = first_Route
    ) {

        composable(
            BaseGraph.Home.route,

            enterTransition = {
                // Слайд влево для входа
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },

            ) {
            Home(
                viewModel = prayerTimeVM,navController = navController)
        }

        // Education Screen
        composable(
            BaseGraph.Education.route,
            enterTransition = {
                scaleIn(tween(600))
            },
        ) {
            Edu(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Practice Screen
        composable(
            BaseGraph.Practice.route,
            enterTransition = {
                scaleIn(tween(600))
            }
        ) {
            Practices(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Settings Screen
        composable(
            BaseGraph.Setting.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(600))
            }
        ) {
            Settings(
                navController = navController, prayerTimeVM)
        }

        composable(
            BaseGraph.Welcome.route,
        ) {
            WelcomeScreen(
                navController,prayerTimeVM,
            )
        }
// Internal Navigation
        composable(InternalGraph.Mosque.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(700))
            }){
            MosqueScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(InternalGraph.PrayerTime.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(600))
            }){
            PrayerTimeScreen(
                viewModel = prayerTimeVM,
                navController = navController
            )
        }

        composable(InternalGraph.QiblaLocation.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(600))
            }){
            QiblaLocScreen(
                viewModel = viewModel,
                navController = navController,

                )
        }

        composable(InternalGraph.Calendar.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(600))
            }){
            CalendarScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(InternalGraph.PrayerTracker.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(600))
            }){
            PrayerTrackerScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(InternalGraph.Book.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(600))
            }){
            BookScreen(
                viewModel = bookViewModel,
                navController = navController,
            )
        }

        composable(InternalGraph.PrayerRead.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(600))
            }){
            PrayerReadScreen(
                viewModel = prayerReadVM,
                navController = navController,
                viewModel2 = prayerTimeVM
            )
        }

        composable(InternalGraph.Tasbeh.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(600))
            }){
            TasbexScreen(
                viewModel = tasbexViewModel,
                navController = navController,
            )
        }
        composable(InternalGraph.Zicry.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(600))
            }){
            ZicryScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(InternalGraph.Dua.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(600))
            }) {
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

        composable(FeatureRoutes.B_Juz.route,
            enterTransition = {
                scaleIn(tween(500))
            }
        ) {
            Juz(
                navController,
                bookViewModel
            )
        }

        composable(FeatureRoutes.B_Bookmarks.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(350))
            }
        ) {
            Bookmarks(
                navController,
                bookViewModel
            )
        }

        composable(
            "b_book_dt/{suraId}",
            arguments = listOf(navArgument("suraId") { type = NavType.IntType }),
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(600))
            }
        ) { backStackEntry ->

            val suraId = backStackEntry.arguments?.getInt("suraId") ?: 0

            BookDetail(
                viewModel = bookViewModel,
                surahId = suraId
            )
        }

        composable(
            "b_juz_dt/{juzId}/{surId}",
            arguments = listOf(
                navArgument("juzId") { type = NavType.IntType },
                navArgument("surId") { type = NavType.IntType }
            ),
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(600))
            }
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

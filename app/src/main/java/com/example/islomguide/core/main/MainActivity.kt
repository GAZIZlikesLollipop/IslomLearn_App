package com.example.islomguide.core.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.IslomGuideTheme
import com.example.islomguide.R
import com.example.islomguide.core.data.network.RetrofitClient
import com.example.islomguide.core.data.repository.NetworkBookRepository
import com.example.islomguide.core.data.repository.NetworkPrayerTimeRepository
import com.example.islomguide.core.main.Routes.BaseGraph
import com.example.islomguide.core.main.Routes.FeatureRoutes
import com.example.islomguide.core.main.Routes.InternalGraph
import com.example.islomguide.core.ui_kit.BaseAppTopBaseBar
import com.example.islomguide.core.ui_kit.BottomAppBar
import com.example.islomguide.core.ui_kit.InternalTopAppBar
import com.example.islomguide.islom.components.BookTopBar
import com.example.islomguide.islom.components.PRTopBar
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModelFactory
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.PrayerReadVM
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModelFactory
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

class MainActivity : ComponentActivity() {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "location")

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "NewApi",
        "StateFlowValueCalledInComposition"
    )
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val bookRepository = NetworkBookRepository(RetrofitClient.bookApi)
        val bookViewModel: BookViewModel = ViewModelProvider(this, BookViewModelFactory(bookRepository))[BookViewModel::class.java]


        val userLocationRepository = com.example.islomguide.core.data.repository.UserLocationRepository(dataStore)

        val prayerTimesRepository = NetworkPrayerTimeRepository(RetrofitClient.aladhanApi)
        val prayerTimeVMFactory = PrayerTimeViewModelFactory(prayerTimesRepository, userLocationRepository)
        val prayerReadVM: PrayerReadVM = ViewModelProvider(this)[PrayerReadVM::class.java]

        val prayerTimeViewModel = ViewModelProvider(this, prayerTimeVMFactory)[PrayerTimeViewModel::class.java]

        setContent {
            IslomGuideTheme {

                val first_Route = if(prayerTimeViewModel.selectedCountry.isNotEmpty() && prayerTimeViewModel.selectedCity.isNotEmpty()){
                    BaseGraph.Home.route
                }else{
                    BaseGraph.Welcome.route
                }
                val state = prayerReadVM.state.value
                val gender_arr = stringArrayResource(R.array.gender_choice)
                val gender = when {
                    state.isMan -> gender_arr[4]
                    state.isWoman -> gender_arr[5]
                    else -> ""
                }
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route
                Log.d("CrrentRoute", "Current route is: $currentRoute")

                    Scaffold(
                        topBar = {
                            when(currentRoute) {
                                BaseGraph.Practice.route, BaseGraph.Home.route , BaseGraph.Education.route , BaseGraph.Setting.route, BaseGraph.Welcome.route  -> BaseAppTopBaseBar(navController)
                                InternalGraph.PrayerTime.route,InternalGraph.PrayerTracker.route,InternalGraph.Calendar.route,InternalGraph.Mosque.route,InternalGraph.QiblaLocation.route,InternalGraph.PrayerRead.route,InternalGraph.Dua.route,InternalGraph.Tasbeh.route,InternalGraph.Zicry.route -> InternalTopAppBar(navController)
                                InternalGraph.Book.route, FeatureRoutes.B_Juz.route, FeatureRoutes.B_Bookmarks.route -> BookTopBar(navController)
                                FeatureRoutes.PR_Fajr.route, FeatureRoutes.PR_Asr.route,FeatureRoutes.PR_Magrib.route,FeatureRoutes.PR_Zuhr.route,FeatureRoutes.PR_Isha.route -> PRTopBar(gender,navController)
                            }
                        },
                        bottomBar = {
                            if( currentRoute == BaseGraph.Home.route ||currentRoute == BaseGraph.Education.route ||currentRoute == BaseGraph.Practice.route ||currentRoute == BaseGraph.Setting.route && currentRoute != BaseGraph.Welcome.route) {
                                BottomAppBar(
                                    navController
                                )
                            }
                        },
                        content = {
                            Navigation(navController, first_Route)
                        },
                        containerColor = MaterialTheme.colorScheme.onSecondary
                    )

            }
        }
    }
}

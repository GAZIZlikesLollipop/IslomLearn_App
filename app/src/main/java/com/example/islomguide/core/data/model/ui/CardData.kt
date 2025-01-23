package com.example.islomguide.core.data.model.ui

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Mosque
import androidx.compose.material.icons.rounded.TrackChanges
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.main.Routes.BaseGraph
import com.example.islomguide.core.main.Routes.InternalGraph

class CardData {

    fun getButtonCardItems(context: Context,navController : NavController): List<ButtonNavCard>{

        val currentRoute = navController.currentBackStackEntry?.destination?.route
        val titles = context.resources.getStringArray(R.array.inrernal_sections)

        return when(currentRoute){
            BaseGraph.Home.route -> {
                listOf(
                    ButtonNavCard(
                        title = titles[10],
                        route = InternalGraph.PrayerTracker,
                        image = null,
                        icon = Icons.Rounded.TrackChanges
                    ),
                    ButtonNavCard(
                        title = titles[7],
                        route = InternalGraph.Calendar,
                        image = null,
                        icon = Icons.Rounded.CalendarMonth
                    ),
                    ButtonNavCard(
                        title = titles[6],
                        route = InternalGraph.Mosque,
                        image = null,
                        icon = Icons.Rounded.Mosque
                    ),
                    ButtonNavCard(
                        title = titles[5],
                        route = InternalGraph.QiblaLocation,
                        image = R.drawable.qibla_loc2,
                        icon = null
                    ),
                )
            }

            BaseGraph.Education.route -> {
                listOf(
                    ButtonNavCard(
                        title = titles[1],
                        route = InternalGraph.Book,
                        image = R.drawable.quran,
                        icon = null
                    ),
                    ButtonNavCard(
                        title = titles[8],
                        route = InternalGraph.IslamBaseGuide,
                        image = R.drawable.islom_learn,
                        icon = null
                    ),
                    ButtonNavCard(
                        title = titles[3],
                        route = InternalGraph.PrayerRead,
                        image = R.drawable.namoz_read,
                        icon = null
                    )
               )
            }

            BaseGraph.Practice.route -> {
                listOf(
                    ButtonNavCard(
                        title = titles[0],
                        route = InternalGraph.Dua,
                        image = R.drawable.dua,
                        icon = null
                    ),
                    ButtonNavCard(
                        title = titles[2],
                        route = InternalGraph.Tasbeh,
                        image = R.drawable.tasbex,
                        icon = null
                    ),
                    ButtonNavCard(
                        title = titles[9],
                        route = InternalGraph.Zicry,
                        image = R.drawable.zicry,
                        icon = null
                    )
                )
            }
            else -> emptyList()
        }
    }
}

package com.example.islomguide.core.ui_kit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.islomguide.R
import com.example.islomguide.core.main.Routes.BaseGraph
import com.example.islomguide.core.main.Routes.FeatureRoutes
import com.example.islomguide.core.main.Routes.InternalGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppTopBaseBar(
    navController: NavController,
    head : TextStyle = MaterialTheme.typography.headlineLarge
) {
    val sections = stringArrayResource(id = R.array.sections)
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    // Используем элементы перечисления navGraph напрямую
    val currentSec = when (currentRoute) {
        BaseGraph.Home.route -> sections[0]
        BaseGraph.Education.route -> sections[1]
        BaseGraph.Practice.route -> sections[2]
        BaseGraph.Setting.route -> sections[3]
        else -> ""
    }
    TopAppBar(
        title = {
            Text(
                text = currentSec,
                style = head
            )

        },
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp),
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InternalTopAppBar(
    navController: NavController
) {
    val sections = stringArrayResource(id = R.array.inrernal_sections)
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    // Используем элементы перечисления navGraph напрямую
    val currentSec = when (currentRoute) {
        InternalGraph.Dua.route -> sections[0]
        InternalGraph.Book.route, FeatureRoutes.B_Juz.route, FeatureRoutes.B_Bookmarks.route -> sections[1]
        InternalGraph.Tasbeh.route -> sections[2]
        InternalGraph.PrayerRead.route -> sections[3]
        InternalGraph.PrayerTime.route -> sections[4]
        InternalGraph.QiblaLocation.route -> sections[5]
        InternalGraph.Mosque.route -> sections[6]
        InternalGraph.Calendar.route -> sections[7]
        InternalGraph.Zicry.route -> sections[8]
        InternalGraph.PrayerTracker.route -> sections[9]
        else -> ""
    }

    TopAppBar(
        title = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ){
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                    Modifier
                        .size(36.dp)
                        .offset(y = (-5).dp, x = (-8).dp)
                )
            }
            Text(
                text = currentSec,
                style = if(currentRoute == InternalGraph.PrayerRead.route){
                    MaterialTheme.typography.headlineMedium
                }else{
                    MaterialTheme.typography.headlineLarge
                },
                modifier = Modifier.padding(
                    if(currentRoute == InternalGraph.PrayerRead.route){
                        PaddingValues(horizontal = 30.dp)
                    }else{
                        PaddingValues(horizontal = 50.dp)
                    }
                )
            )

        },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
        ,
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
    )
}
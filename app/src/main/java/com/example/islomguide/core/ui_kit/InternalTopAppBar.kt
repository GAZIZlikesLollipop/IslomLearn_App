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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.main.Routes
import com.example.islomguide.core.main.Routes.InternalGraph
import com.example.islomguide.islom.logic.IslomViewModel
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.PrayerReadVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InternalTopAppBar(
    navController: NavController
) {
    val sections = stringArrayResource(id = R.array.inrernal_sections)
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    // Используем элементы перечисления navGraph напрямую
    val currentSec = when (currentRoute) {
        InternalGraph.Dua.name -> sections[0]
        InternalGraph.Quran.name -> sections[1]
        InternalGraph.Tasbeh.name -> sections[2]
        InternalGraph.Prayer_Read.name -> sections[3]
        InternalGraph.Prayer_Time.name -> sections[4]
        InternalGraph.Qibla_Location.name -> sections[5]
        InternalGraph.Mosque.name -> sections[6]
        InternalGraph.Calendar.name -> sections[7]
        InternalGraph.Islom_Base_Guide.name -> sections[9]
        InternalGraph.Zicry.name -> sections[9]
        InternalGraph.Prayer_Tracker.name -> sections[10]
        else -> sections[0]
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
                style = if(currentRoute == InternalGraph.Prayer_Read.name){
                    MaterialTheme.typography.headlineMedium
                }else{
                    MaterialTheme.typography.headlineLarge
                },
                modifier = Modifier.padding(
                    if(currentRoute == InternalGraph.Prayer_Read.name){
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
package com.example.islomguide.core.ui_kit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.PrayerReadVM

@Composable
fun CommonInternalScreen(
    navController: NavController,
    content: @Composable () -> Unit,
    ){
    Scaffold(
        topBar = { InternalTopAppBar(navController) },
        content = {paddingValues ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ){
                content()
            }
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.onSecondary),
        containerColor = MaterialTheme.colorScheme.onSecondary
    )
}
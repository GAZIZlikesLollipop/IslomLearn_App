package com.example.islomguide.core.ui_kit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.islomguide.islom.components.ButtonCard

@Composable
fun CommonBaseScreen(
    navController: NavController,
    content: @Composable () -> Unit,

){
    Scaffold(
        topBar = { BaseAppTopBaseBar(navController) },
        bottomBar = { BottomAppBar(navController) },
        content = {paddingValues ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ){
                ButtonCard(navController)
                content()
            }
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.onSecondary),
        containerColor = MaterialTheme.colorScheme.onSecondary
    )
}
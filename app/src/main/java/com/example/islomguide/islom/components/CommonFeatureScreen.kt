package com.example.islomguide.islom.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun CommonFeatureScreen(
    navController: NavController,
    content: @Composable () -> Unit,
    topAppBar : @Composable () -> Unit
    ){
        Scaffold(
            topBar = { topAppBar() },
            content = {
                paddingValues ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ){
                    content()
                }
            },
            containerColor = MaterialTheme.colorScheme.onSecondary
        )
    }

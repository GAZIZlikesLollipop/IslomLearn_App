package com.example.islomguide.islom.ui.screen.practicesScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.islomguide.core.ui_kit.CommonBaseScreen
import com.example.islomguide.islom.logic.IslomViewModel

@Composable
fun Practices(
    modifier : Modifier = Modifier,
    navController: NavController,
    viewModel : IslomViewModel
){
    CommonBaseScreen(
        navController = navController,
    ){

    }
}
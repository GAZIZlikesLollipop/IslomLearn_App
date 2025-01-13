package com.example.islomguide.islom.screen.Internal.education.BookScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.islomguide.core.ui_kit.CommonInternalScreen
import com.example.islomguide.islom.logic.IslomViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.BookCard
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.NavTopBar

@Composable
fun BookScreen(
    navController: NavController,
    viewModel: BookViewModel,
){

    LaunchedEffect(Unit){
        viewModel.getSurah(1)
    }

    CommonInternalScreen(
        navController
    ) {
        NavTopBar(navController)
        BookCard(viewModel,1)
        BookCard(viewModel,2)
    }
}
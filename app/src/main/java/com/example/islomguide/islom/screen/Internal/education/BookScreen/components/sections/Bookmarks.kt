package com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.islomguide.core.ui_kit.CommonInternalScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.NavTopBar

@Composable
fun Bookmarks(
    navController: NavController,
    viewModel: BookViewModel
){
    CommonInternalScreen(navController) {
        NavTopBar(navController)
    }

}
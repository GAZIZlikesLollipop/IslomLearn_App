package com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.islomguide.islom.logic.IslomViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel

@Composable
fun Bookmarks(
    navController: NavController,
    viewModel: BookViewModel
){
    Box {
        BookScreen(navController, viewModel)
    }
}
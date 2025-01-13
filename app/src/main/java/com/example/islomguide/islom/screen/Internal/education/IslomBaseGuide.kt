package com.example.islomguide.islom.screen.Internal.education

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.islomguide.core.ui_kit.CommonInternalScreen
import com.example.islomguide.islom.logic.IslomViewModel

@Composable
fun IslomBaseGuideScreen(
    navController: NavController,
    viewModel: IslomViewModel,
    modifier: Modifier = Modifier
){
    CommonInternalScreen(
        navController
    ) {

    }
}
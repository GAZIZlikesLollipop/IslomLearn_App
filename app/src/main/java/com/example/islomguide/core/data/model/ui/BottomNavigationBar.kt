package com.example.islomguide.core.data.model.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.islomguide.core.main.Routes.BaseGraph

data class BottomNavigationBar(
    val name : String,
    val icon : ImageVector,
    val route : BaseGraph
)
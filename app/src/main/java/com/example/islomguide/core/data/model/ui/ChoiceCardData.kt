package com.example.islomguide.core.data.model.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.islomguide.core.main.Routes.FeatureRoutes

data class ChoiceCardData(
    val name: String,
    val icon: ImageVector,
    val route: FeatureRoutes?
)

package com.example.islomguide.core.data.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.islomguide.core.main.Routes.InternalGraph

data class ButtonNavCard(
    val image: Int?,
    val icon: ImageVector?,
    val title: String,
    val route: InternalGraph
)

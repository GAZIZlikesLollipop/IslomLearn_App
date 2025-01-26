package com.example.islomguide.islom.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.core.main.Routes
import com.example.islomguide.core.main.Routes.FeatureRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTopBar(
    text : String,
    navController: NavController
){
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    TopAppBar(
        title = {
            IconButton(
                onClick = {
                    when(currentRoute){
                        FeatureRoutes.B_BookDT.route -> navController.navigate(Routes.InternalGraph.Book.route)
                        FeatureRoutes.B_JuzDT.route -> navController.navigate(FeatureRoutes.B_Juz.route)
                        FeatureRoutes.B_BMDT.route -> navController.navigate(FeatureRoutes.B_Bookmarks.route)
                        FeatureRoutes.B_Juz.route -> navController.navigate(Routes.BaseGraph.Education.route)
                        else -> navController.popBackStack()
                    }
                }
            ){
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back",
                    Modifier
                        .size(36.dp)
                        .offset(y = (-5).dp, x = (-8).dp)
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(horizontal = 50.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
        ,
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
    )
}
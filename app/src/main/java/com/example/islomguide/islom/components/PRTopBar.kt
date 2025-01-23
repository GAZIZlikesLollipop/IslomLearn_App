@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.islomguide.islom.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.islomguide.core.main.Routes
import com.example.islomguide.core.main.Routes.FeatureRoutes
import com.example.islomguide.core.main.Routes.InternalGraph
import com.example.islomguide.islom.logic.IslomViewModel


@Composable
fun PRTopBar(
    headText : String,
    navController: NavController
){
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val viewModel : IslomViewModel = viewModel()
    val state  = viewModel.state.collectAsState()
    val icon : ImageVector = if(state.value.content){
        Icons.Default.ArrowUpward
    }else{
        Icons.Default.ArrowDownward
    }

    TopAppBar(
        title = {
            IconButton(
                onClick = {
                    when(currentRoute){
                        InternalGraph.Book.route , FeatureRoutes.B_Juz.route , FeatureRoutes.B_Bookmarks.route -> navController.navigate(Routes.BaseGraph.Education.route)
                        FeatureRoutes.B_BookDT.route -> {
                            navController.navigate(InternalGraph.Book.route)
                        }
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
                text = headText,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(horizontal = 50.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            if(viewModel.CheckRoute(navController)) {
                IconButton(
                    onClick = {
                        viewModel.checkContent()
                    }, Modifier
                        .offset(x = 325.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Close",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }

        },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
        ,
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
    )
}

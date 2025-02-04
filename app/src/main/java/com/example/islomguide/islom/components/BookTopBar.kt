package com.example.islomguide.islom.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.islomguide.R
import com.example.islomguide.core.main.Routes
import com.example.islomguide.core.main.Routes.BaseGraph
import com.example.islomguide.core.main.Routes.FeatureRoutes
import com.example.islomguide.core.main.Routes.InternalGraph
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.BookTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTopBar(
    navController: NavController,
    name: String? = null
){
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val array = stringArrayResource(R.array.inrernal_sections)[1]

    val text = if(name?.isNotEmpty() == true){
        name
    }else{
        array
    }
    Column {
        TopAppBar(
            title = {
                Row {
                    IconButton(
                        onClick = {
                            when (currentRoute) {
                                FeatureRoutes.B_Juz.route -> navController.navigate(BaseGraph.Education.route)
                                FeatureRoutes.B_Bookmarks.route -> navController.navigate(BaseGraph.Education.route)
                                InternalGraph.Book.route -> navController.navigate(BaseGraph.Education.route)
                                else -> navController.popBackStack()
                            }
                        }
                    ) {
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
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
        )

        if (currentRoute == InternalGraph.Book.route || currentRoute == FeatureRoutes.B_Bookmarks.route || currentRoute == FeatureRoutes.B_Juz.route) {
            NavTopBar(navController)
        }
    }
}

@Composable
fun NavTopBar(
    navController: NavController
){
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val context = LocalContext.current
    val sections = context.resources.getStringArray(R.array.book_sections)

    val items = listOf(
        BookTopBar(
            text = sections[0],
            route = InternalGraph.Book.route
        ),
        BookTopBar(
            text = sections[1],
            route = FeatureRoutes.B_Juz.route
        ),
        BookTopBar(
            text = sections[2],
            route = FeatureRoutes.B_Bookmarks.route
        ),
    )
    NavigationBar(
        modifier = Modifier
            .height(60.dp),
        tonalElevation = 0.dp,

        ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth().height(70.dp)

        ) {
            items.forEachIndexed{index, item ->
                val isSelected = currentRoute == item.route
                NavigationBarItem(
                    selected = false,
                    onClick = {  },
                    label = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextButton(
                                onClick = {navController.navigate(item.route)},
                                Modifier.fillMaxSize(),
                                shape = RoundedCornerShape(0.dp)
                            ){
                                Text(
                                    item.text,
                                    color = if (isSelected) {
                                        MaterialTheme.colorScheme.onSurface
                                    } else {
                                        MaterialTheme.colorScheme.inverseOnSurface
                                    },
                                    style = MaterialTheme.typography.headlineMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                            if(isSelected){
                                HorizontalDivider(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    thickness = 2.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .offset(y = (7).dp)
                                )
                            }
                        }
                    },
                    icon = {},
                    alwaysShowLabel = true
                )
            }
        }
    }
}

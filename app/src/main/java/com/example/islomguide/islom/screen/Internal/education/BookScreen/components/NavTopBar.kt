package com.example.islomguide.islom.screen.Internal.education.BookScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.main.Routes
import com.example.islomguide.core.main.Routes.FeatureRoutes


@Composable
fun NavTopBar(
    navController: NavController
){
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val context = LocalContext.current
    val sections = context.resources.getStringArray(R.array.book_sections)

    val items = listOf(
        BookTopBar(
            text = sections[0],
            route = Routes.InternalGraph.Book.name
        ),
        BookTopBar(
            text = sections[1],
            route = FeatureRoutes.B_Juz.name
        ),
        BookTopBar(
            text = sections[2],
            route = FeatureRoutes.B_Bookmarks.name
        ),
    )
    NavigationBar(
        modifier = Modifier
            .height(60.dp)
            .background(MaterialTheme.colorScheme.background)
            .offset(y = (-1).dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .height(60.dp)

        ) {
            items.forEachIndexed{index, item ->
                val isSelected = currentRoute == item.route
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {  },
                    label = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextButton(
//                                navController.navigate(item.route.name)
                                onClick = {navController.navigate(item.route)},
                                Modifier
                                    .height(50.dp)
                                    .width(125.dp)
                                ,
                                shape = RoundedCornerShape(0.dp)
                            ){
                                Text(
                                    item.text,
                                    color = if (isSelected) {
                                        MaterialTheme.colorScheme.onSurface
                                    } else {
                                        MaterialTheme.colorScheme.inverseOnSurface
                                    },
                                    style = MaterialTheme.typography.headlineSmall,
                                    textAlign = TextAlign.Center
                                )
                            }
                            if(isSelected){
                                HorizontalDivider(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    thickness = 3.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .offset(y = -(10).dp)
                                )
                            }
                        }
                    },
                    icon = {},

                    alwaysShowLabel = true, // Показывать текст всегда
                )

            }
        }
    }
}

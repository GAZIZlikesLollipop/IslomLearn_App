package com.example.islomguide.core.ui_kit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Brightness3
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ImportContacts
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.data.model.ui.BottomNavigationBar
import com.example.islomguide.core.main.Routes.BaseGraph


@Composable
fun BottomAppBar(
    navController: NavController
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val sections = stringArrayResource(id = R.array.sections)

    val items = listOf(
        BottomNavigationBar(
            name = sections[0],
            icon = Icons.Rounded.Home,
            route = BaseGraph.Home
        ),
        BottomNavigationBar(
            name = sections[1],
            icon = Icons.Rounded.ImportContacts,
            route = BaseGraph.Education
        ),
        BottomNavigationBar(
            name = sections[2],
            icon = Icons.Rounded.Brightness3,
            route = BaseGraph.Practice
        ),
        BottomNavigationBar(
            name = sections[3],
            icon = Icons.Default.Settings,
            route = BaseGraph.Setting
        )
    )

    NavigationBar {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .offset(y = 5.dp)
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = currentRoute == item.route.name,
                    onClick = {
                        navController.navigate(route = item.route.name)
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(
                            text = item.name,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        }
    }
}
package com.example.islomguide.core.ui_kit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.main.Routes.BaseGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppTopBaseBar(
    navController: NavController,
    head : TextStyle = MaterialTheme.typography.headlineLarge
) {
    val sections = stringArrayResource(id = R.array.sections)
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    // Используем элементы перечисления navGraph напрямую
    val currentSec = when (currentRoute) {
        BaseGraph.Home.name -> sections[0]
        BaseGraph.Education.name -> sections[1]
        BaseGraph.Practice.name -> sections[2]
        BaseGraph.Setting.name -> sections[3]
        else -> sections[0]
    }

    TopAppBar(
        title = {
            Text(
                text = currentSec,
                style = head
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
        ,
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
    )
}


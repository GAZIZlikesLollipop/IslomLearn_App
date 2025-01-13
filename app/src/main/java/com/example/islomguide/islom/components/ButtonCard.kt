@file:OptIn(ExperimentalLayoutApi::class)

package com.example.islomguide.islom.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.core.data.model.ui.ButtonNavCard
import com.example.islomguide.core.data.model.ui.CardData
import com.example.islomguide.core.main.Routes.BaseGraph


@Composable
fun ButtonCard(navController: NavController) {
    val context = LocalContext.current
    val cardInfo = CardData().getButtonCardItems(context = context, navController = navController)

    // Проверяем, что индекс в допустимых пределах
    
    FlowRow(
        modifier = getDynamicPadding(navController),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ){
        cardInfo.forEach { card ->
            val paddingValues : Dp =
                when{
                    card.title.length == 9 -> 32.dp
                    card.title.length == 5 || card.title.length == 6 -> 50.dp
                    card.title.length == 3 -> 52.dp
                    card.title.isBlank() -> 2.dp
                    else -> 12.dp
                }
            Column {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(CircleShape)
                        .size(125.dp)
                        .clickable {
                            navController.navigate(card.route.name) // Навигация
                        },
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.inverseOnSurface)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        // Отображение изображения или иконки
                        ImageOrIcon(card)
                        // За22головок карточки
                    }
                }
                Text(
                    text = card.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(horizontal = paddingValues)
                        .offset(y = (-16).dp)
                )
            }
        }
    }
}
@Composable
fun getDynamicPadding(navController: NavController): Modifier {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    return when (currentRoute) {
        BaseGraph.Home.name -> Modifier
            .offset(y = 255.dp)
            .padding(16.dp)
        else -> Modifier.padding(16.dp)
    }
}

@Composable
fun ImageOrIcon(card: ButtonNavCard) {
        if (card.image != null) {
            Image(
                painter = painterResource(card.image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(32.dp)),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primaryContainer)
            )
        } else if (card.icon != null) {
            Icon(
                imageVector = card.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(32.dp)),
                tint = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }


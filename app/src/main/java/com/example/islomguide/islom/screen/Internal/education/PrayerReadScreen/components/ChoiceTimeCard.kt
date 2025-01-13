package com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.islomguide.islom.components.PrayerTimeChoice
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChoiceTimeCard(
    navController: NavController,
    viewModel: PrayerTimeViewModel
    ){
    val context = LocalContext.current
    val cardsInfo = PrayerTimeChoice().getTimeChoiceCards(context,navController,viewModel)
    FlowRow(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(11.dp)
    ){
        cardsInfo.forEach { card ->
            Column(
                Modifier.offset(y = 16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(120.dp)
                        .clickable {
                            card.route?.let { navController.navigate(it.name) } // Навигация
                        },
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.inverseOnSurface)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        // Отображение изображения или иконки
                        Icon(
                            imageVector = card.icon,
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(32.dp)),
                            tint = MaterialTheme.colorScheme.primaryContainer
                        )
                        // Заголовок карточки
                    }
                }
                Text(
                    text = card.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .offset(y = (-16).dp)
                )
            }
        }
    }
}

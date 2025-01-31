package com.example.islomguide.islom.ui.screen.homeScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.main.Routes
import com.example.islomguide.core.main.Routes.BaseGraph
import com.example.islomguide.core.main.Routes.InternalGraph
import com.example.islomguide.core.ui_kit.CommonBaseScreen
import com.example.islomguide.core.ui_kit.ErrorScreen
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeUiState
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel
import kotlinx.coroutines.delay

@SuppressLint("NewApi", "SuspiciousIndentation", "UnusedContentLambdaTargetStateParameter")
@Composable
fun Home(
    navController: NavController,
    viewModel : PrayerTimeViewModel
) {
    val uiState = viewModel.prayerTimeUiState
    val context = LocalContext.current
    var country by remember { mutableStateOf(viewModel.selectedCountry) }

    LaunchedEffect(Unit) {
        viewModel.setCountry(context, viewModel.getCity())
    }

    val time = viewModel.currentTime
    val nextPrayerTime = viewModel.currentTimeToNextPrayer

        CommonBaseScreen(navController = navController) {
            LaunchedEffect(Unit) {
                viewModel.getCurrentDateAndPrayerTimes()
            }
            Box {
                AnimatedContent(
                    uiState,
                    transitionSpec = {
                        expandVertically(
                            animationSpec = tween(800)
                        ) togetherWith scaleOut(animationSpec = tween(800))
                    },
                ) {
                when (uiState) {
                    is PrayerTimeUiState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(100.dp)
                                .offset(x = 150.dp, y = 115.dp)
                        )
                    }

                    is PrayerTimeUiState.Error -> {
                        ErrorScreen(
                            { viewModel.getCurrentDateAndPrayerTimes() },
                            modifier = Modifier.fillMaxSize().offset(y = (-200).dp)
                        )
                    }

                    is PrayerTimeUiState.Success -> {
                        val timings = uiState.text
                        LaunchedEffect(key1 = true) {
                            while (true) {
                                delay(1000)  // обновляем каждую секунду
                                viewModel.updateCurrentTime(context)
                                viewModel.updateTimeToNextPrayer(context, timings)
                            }
                        }

                        val currentPrayer = viewModel.getCurrentPrayer(context, timings)

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .size(height = 210.dp, width = 128.dp)
                                .offset(y = 64.dp)
                                .clickable {
                                    navController.navigate(InternalGraph.PrayerTime.route) // Навигация
                                },
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                            shape = RoundedCornerShape(36.dp)

                        ) {
                            Column(
                                modifier =Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    time,
                                    style = MaterialTheme.typography.displayLarge,
                                    textAlign = TextAlign.Right,
                                    color = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier


                                )
                                Text(
                                    currentPrayer,
                                    style = MaterialTheme.typography.displayLarge,
                                    textAlign = TextAlign.Right,
                                    color = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier

                                )
                                Text(
                                    nextPrayerTime,
                                    style = MaterialTheme.typography.displayLarge,
                                    textAlign = TextAlign.Right,
                                    color = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier

                                )
                            }
                        }
                    }
                }
            }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun WelcomeScreen(
        navController: NavController,
        viewModel: PrayerTimeViewModel,
    ) {

        val context = LocalContext.current
        val city_choose = stringResource(R.string.city_choose)
        val cities = stringArrayResource(R.array.cities)
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.onSecondary)
                .fillMaxSize(),
        ) {
            Card(
                Modifier
                    .align(Alignment.Center),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
            ) {
                Spacer(Modifier.padding(vertical = 8.dp))
                Text(
                    city_choose,
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(Modifier.padding(vertical = 12.dp))
                Column(
                    Modifier

                ) {
                    cities.forEach { city ->

                        Surface(
                            onClick = {
                                viewModel.setCity(city)
                                viewModel.setCountry(context, city)
                                navController.navigate(BaseGraph.Home.route)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                city,
                                style = MaterialTheme.typography.displayMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Icon(
                                imageVector = Icons.Rounded.ChevronRight,
                                contentDescription = null,
                                Modifier
                                    .size(50.dp)
                                    .offset(x = 165.dp)
                            )
                        }
                        Spacer(Modifier.padding(vertical = 5.dp))
                    }
                    Spacer(Modifier.padding(vertical = 8.dp))
                }
            }

        }
    }

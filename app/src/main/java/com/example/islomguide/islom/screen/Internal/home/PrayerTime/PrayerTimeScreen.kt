package com.example.islomguide.islom.screen.Internal

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.data.model.network.Timings
import com.example.islomguide.core.ui_kit.CommonFeatureScreen
import com.example.islomguide.core.ui_kit.ErrorScreen
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeUiState
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrayerTimeScreen(
    navController: NavController,
    viewModel: PrayerTimeViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(Unit){
        viewModel.getCurrentDateAndPrayerTimes()
    }
    val uiState = viewModel.prayerTimeUiState

    CommonFeatureScreen {
        Box {
            when (uiState) {
                is PrayerTimeUiState.Success -> {
                    uiState.text?.let { SuccessScreen(times = it, viewModel) }
                }

                is PrayerTimeUiState.Error -> {
                    ErrorScreen(
                        { viewModel.getCurrentDateAndPrayerTimes() },
                        modifier = Modifier.fillMaxSize().align(alignment = Alignment.Center)
                    )
                }

                is PrayerTimeUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(64.dp)
                            .offset(x = 175.dp, y = 275.dp)
                    )
                }
            }
        }
    }
}


@SuppressLint("NewApi")
@Composable
fun SuccessScreen(
    times : Timings,
    viewModel : PrayerTimeViewModel
){

    val context = LocalContext.current
    val prayer_times = context.resources.getStringArray(R.array.prayer_time)
    val current_time = viewModel.currentTime
    val current_date = viewModel.todayDate

    LaunchedEffect(key1 = true) {
        while (true) {
            delay(1000)  // обновляем каждую секунду
            viewModel.updateCurrentTime(context)
            viewModel.updateTimeToNextPrayer(context, times)
        }
    }

    Text(
        current_time,
        style = MaterialTheme.typography.displayLarge,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .offset(y = 50.dp, x = 75.dp)
            .padding(16.dp)
    )
    Card(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .offset(y = 300.dp)
        ,
        shape = RoundedCornerShape(24.dp)
    ) {
        Spacer(Modifier.padding(vertical = 12.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    viewModel.getYesterday(1)
                    viewModel.getCurrentDateAndPrayerTimes()
                }
            ){
                Icon(
                    imageVector = Icons.Rounded.ChevronLeft,
                    contentDescription = null,
                    Modifier
                        .size(75.dp)
                        .weight(1f)
                        .offset(x = 8.dp)
                )
            }

            Text(
                "$current_date",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .weight(2f)
                    .offset(x = 45.dp)
                ,
                color = MaterialTheme.colorScheme.tertiary
            )
            IconButton(
                onClick = {
                    viewModel.getNextDay(1)
                    viewModel.getCurrentDateAndPrayerTimes()
                }
            ){
                Icon(
                    imageVector = Icons.Rounded.ChevronRight,
                    contentDescription = null,
                    Modifier
                        .size(75.dp)
                        .weight(1f)
                        .offset(x = (-8).dp)
                )
            }
        }

        Column(modifier = Modifier.padding(16.dp)){
            times.Fajr?.let {
                Row {
                    Text(
                        "${prayer_times[0]}:",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        it,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.offset(x = 140.dp)
                    )

                }
                Spacer(Modifier.padding(vertical = 5.dp))
            }
            times.Dhuhr?.let {
                Row {
                    Text(
                        "${prayer_times[1]}:",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        it,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.offset(x = 170.dp)
                    )

                }
                Spacer(Modifier.padding(vertical = 5.dp))
            }
            times.Asr?.let {
                Row {
                    Text(
                        "${prayer_times[2]}:",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        it,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.offset(x = 185.dp),
                        color = MaterialTheme.colorScheme.primary
                    )

                }
                Spacer(Modifier.padding(vertical = 5.dp))
            }
            times.Maghrib?.let {
                Row {
                    Text(
                        "${prayer_times[3]}:",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        it,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.offset(x = 140.dp),
                        color = MaterialTheme.colorScheme.primary
                    )

                }
                Spacer(Modifier.padding(vertical = 5.dp))
            }
            times.Isha?.let {
                Row {
                    Text(
                        "${prayer_times[4]}:",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        it,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.offset(x = 180.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(Modifier.padding(vertical = 5.dp))
            }
            Spacer(Modifier.padding(vertical = 5.dp))
        }
    }
}

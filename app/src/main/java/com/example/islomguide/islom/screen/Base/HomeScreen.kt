package com.example.islomguide.islom.ui.screen.homeScreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.main.Routes.InternalGraph
import com.example.islomguide.core.ui_kit.CommonBaseScreen
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeUiState
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel
import kotlinx.coroutines.delay

@SuppressLint("NewApi")
@Composable
fun Home(
    navController: NavController,
    viewModel : PrayerTimeViewModel
){
    val uiState = viewModel.prayerTimeUiState
    val context = LocalContext.current
    var country by remember { mutableStateOf(viewModel.selectedCountry) }

    LaunchedEffect(key1 = country) {
        viewModel.setCountry(context, viewModel.getCity())
    }
    LaunchedEffect(Unit){
        viewModel.getCurrentDateAndPrayerTimes()
    }

    val time = viewModel.currentTime
    val nextPrayerTime = viewModel.currentTimeToNextPrayer

    if(viewModel.selectedCity.isNotEmpty() && viewModel.selectedCountry.isNotEmpty()) {

        CommonBaseScreen(navController = navController) {
            when (uiState) {
                is PrayerTimeUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(100.dp)
                            .offset(x = 150.dp, y = 115.dp)
                    )
                }

                is PrayerTimeUiState.Error -> {
                    ErrorScreen(context, viewModel)
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
                            .size(height = 200.dp, width = 128.dp)
                            .offset(y = 30.dp)
                            .clickable {
                                navController.navigate(InternalGraph.Prayer_Time.name) // Навигация
                            },
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                        shape = RoundedCornerShape(36.dp)

                    ) {
                        Column {
                            Text(
                                "${time}\n",
                                style = MaterialTheme.typography.displayMedium,
                                textAlign = TextAlign.Right,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .offset(x = 75.dp, y = 8.dp)
                            )
                            Text(
                                currentPrayer,
                                style = MaterialTheme.typography.displayMedium,
                                textAlign = TextAlign.Right,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier
                                    .offset(y = (-40).dp, x = 115.dp)
                            )
                            Text(
                                nextPrayerTime,
                                style = MaterialTheme.typography.displayMedium,
                                textAlign = TextAlign.Right,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier
                                    .offset(y = (-25).dp, x = 80.dp)
                            )
                        }
                    }
                    //Test

                }

            }
        }
    }else{
        WelcomeScreen(navController,viewModel,context)
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: PrayerTimeViewModel,
    context: Context
){

    val city_choose = context.getString(R.string.city_choose)
    val cities = context.resources.getStringArray(R.array.cities)
    Box(
        Modifier
            .background(MaterialTheme.colorScheme.onSecondary)
            .fillMaxSize(),
    ){
        Card(
            Modifier
                .align(Alignment.Center),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
        ){
            Spacer(Modifier.padding(vertical = 8.dp))
            Text(
                city_choose,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.padding(vertical = 12.dp))
            Column(
                Modifier

            ){
                cities.forEach { city ->

                    Surface(
                        onClick = {
                            viewModel.setCity(city)
                            viewModel.setCountry(context, city)
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

@SuppressLint("NewApi")
@Composable
fun ErrorScreen(
    context: Context,
    viewModel: PrayerTimeViewModel
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .offset(y = (100).dp)
    ){
        Text(
            context.getString(R.string.error),
            fontSize = 36.sp,
        )
        Spacer(Modifier.padding(vertical = 32.dp))
        Button(
            onClick = {
                viewModel.getCurrentDateAndPrayerTimes()
            }
        ){
            Text(
                context.getString(R.string.try_again),
                fontSize = 24.sp
            )
        }
    }
}

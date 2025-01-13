package com.example.islomguide.islom.components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeInactive
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.NightsStay
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material.icons.rounded.WbTwilight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.data.model.ui.ChoiceCardData
import com.example.islomguide.core.main.Routes.FeatureRoutes
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeUiState
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel

private val Icons.Filled.prayer_times: ImageVector

    @Composable
    get() {
        return ImageVector.vectorResource(R.drawable.prayer_times)
    }

class PrayerTimeChoice {

    @SuppressLint("NewApi")
    @Composable
    fun getTimeChoiceCards(
        context: Context,
        navController: NavController,
        viewModel: PrayerTimeViewModel
    ): List<ChoiceCardData> {
        LaunchedEffect(Unit) {
            viewModel.getCurrentDateAndPrayerTimes()
        }

        val uiState = viewModel.prayerTimeUiState
        val times = context.resources.getStringArray(R.array.prayer_time)

        val lisy: ChoiceCardData = when (uiState) {
            is PrayerTimeUiState.Success -> {
                val timtings = uiState.text
                ChoiceCardData(
                    name = times[6],
                    route = viewModel.getPrayerGraph(context, timtings),
                    icon = Icons.Default.prayer_times
                )
            }
            is PrayerTimeUiState.Error -> {
                ChoiceCardData(
                    name = "Error",
                    route = null,
                    icon = Icons.Default.ErrorOutline
                )
            }
            is PrayerTimeUiState.Loading -> {
                ChoiceCardData(
                    name = "Wait, please",
                    route = null,
                    icon = Icons.Default.Loop
                )
            }

            else -> {
                ChoiceCardData(
                    name = "",
                    route = null,
                    icon = Icons.Default.AirplanemodeInactive
                )
            }
        }
        return listOf(
            ChoiceCardData(
                name = times[0],
                route = FeatureRoutes.PR_Fajr,
                icon = Icons.Rounded.NightsStay
            ),
            ChoiceCardData(
                name = times[1],
                route = FeatureRoutes.PR_Zuhr,
                icon = Icons.Rounded.WbSunny
            ),
            ChoiceCardData(
                name = times[2],
                route = FeatureRoutes.PR_Asr,
                icon = Icons.Rounded.WbTwilight
            ),
            ChoiceCardData(
                name = times[3],
                route = FeatureRoutes.PR_Magrib,
                icon = Icons.Rounded.WbTwilight
            ),
            ChoiceCardData(
                name = times[4],
                route = FeatureRoutes.PR_Isha,
                icon = Icons.Rounded.DarkMode
            ),
            lisy
        )
    }
}

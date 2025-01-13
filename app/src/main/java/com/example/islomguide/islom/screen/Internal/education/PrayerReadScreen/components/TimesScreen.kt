package com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.main.Routes.FeatureRoutes
import com.example.islomguide.islom.components.CommonFeatureScreen
import com.example.islomguide.islom.components.FeatureTopBar
import com.example.islomguide.islom.logic.IslomViewModel
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.PrayerReadVM
import kotlinx.coroutines.launch

@Composable
fun TimesScreen(
    navController: NavController,
    viewModel: PrayerReadVM
) {
    val context = LocalContext.current
    val sections = context.resources.getStringArray(R.array.prayer_sections)
    val state = viewModel.state.collectAsState().value


    val fajr = context.resources.getStringArray(R.array.fajr)
    val zuhr = context.resources.getStringArray(R.array.zuhr)
    val asr = context.resources.getStringArray(R.array.asr)
    val magrib = context.resources.getStringArray(R.array.magrib)
    val isha = context.resources.getStringArray(R.array.isha)
    val actions = context.resources.getStringArray(R.array.prayer_actions)

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val islomVM: IslomViewModel = viewModel()
    val islomState = islomVM.state.collectAsState()
    // Для topAppBAr
    val prayerTimes = context.resources.getStringArray(R.array.prayer_time)
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val gender_arr = context.resources.getStringArray(R.array.gender_choice)
    val currentTime = when (currentRoute) {
        FeatureRoutes.PR_Fajr.name -> prayerTimes[0]
        FeatureRoutes.PR_Zuhr.name -> prayerTimes[1]
        FeatureRoutes.PR_Asr.name -> prayerTimes[2]
        FeatureRoutes.PR_Magrib.name -> prayerTimes[3]
        FeatureRoutes.PR_Isha.name -> prayerTimes[4]
        else -> prayerTimes[6]
    }
    val gender = when {
        state.isMan -> gender_arr[4]
        state.isWoman -> gender_arr[5]
        else -> ""
    }

    CommonFeatureScreen(
        topAppBar = { FeatureTopBar("$currentTime $gender", navController) },
        navController = navController,
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                // Содержание
                if (islomState.value.content) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        item {
                            Spacer(Modifier.padding(vertical = 32.dp))
                            Text(
                                "Содержание: ",
                                style = MaterialTheme.typography.displayLarge,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                        items(sections.size) { index ->
                            Text(
                                text = sections[index],
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    textDecoration = TextDecoration.Underline
                                ),
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier
                                    .clickable {
                                        // Прокрутка до выбранной секции
                                        coroutineScope.launch {
                                            listState.animateScrollToItem(index)
                                        }
                                    }
                                    .padding(vertical = 8.dp)
                            )
                        }
                        item {
                            Divider(Modifier.padding(vertical = 12.dp))
                        }
                    }
                }
                LazyColumn(
                    state = listState,
                    modifier = Modifier.padding(16.dp)
                ) {
                    when {
                        state.isMan -> {
                            when(currentRoute){
                                FeatureRoutes.PR_Fajr.name ->{
                                    FirstRakatMen(navController,context,actions[0],fajr,zuhr,asr,magrib,isha)
                                    FourthRakatMen(context)
                                    NotFirstRakatMen(context,actions[1])
                                    FourthRakatMen(context)
                                }
                                FeatureRoutes.PR_Zuhr.name ->{
                                    FirstRakatMen(navController,context,actions[0],fajr,zuhr,asr,magrib,isha)
                                    SecondRakatMen(context)
                                    ThirdRakatMen(context)
                                    FourthRakatMen(context)
                                    NotFirstRakatMen(context,actions[1])
                                    SecondRakatMen(context)
                                    ThirdRakatMen(context)
                                    FourthRakatMen(context)
                                    NotFirstRakatMen(context,actions[1])
                                    FourthRakatMen(context)
                                }
                                FeatureRoutes.PR_Asr.name ->{
                                    FirstRakatMen(navController,context,actions[0],fajr,zuhr,asr,magrib,isha)
                                    SecondRakatMen(context)
                                    ThirdRakatMen(context)
                                    FourthRakatMen(context)
                                }
                                FeatureRoutes.PR_Magrib.name ->{
                                    FirstRakatMen(navController,context,actions[0],fajr,zuhr,asr,magrib,isha)
                                    SecondRakatMen(context)
                                    FourthRakatMen(context)
                                    NotFirstRakatMen(context,actions[1])
                                    FourthRakatMen(context)
                                }
                                FeatureRoutes.PR_Isha.name ->{
                                    FirstRakatMen(navController,context,actions[0],fajr,zuhr,asr,magrib,isha)
                                    SecondRakatMen(context)
                                    ThirdRakatMen(context)
                                    FourthRakatMen(context)
                                    NotFirstRakatMen(context,actions[1])
                                    FourthRakatMen(context)
                                }
                            }
                        }
                        state.isWoman -> {
                            when(currentRoute){
                                FeatureRoutes.PR_Fajr.name ->{
                                    FirstRakatWomen(navController,context,actions[0],fajr,zuhr,asr,magrib,isha)
                                    FourthRakatWomen(context)
                                    NotFirstRakatWomen(context,actions[1])
                                    FourthRakatWomen(context)
                                }
                                FeatureRoutes.PR_Zuhr.name ->{
                                    FirstRakatWomen(navController,context,actions[0],fajr,zuhr,asr,magrib,isha)
                                    SecondRakatWomen(context)
                                    ThirdRakatWomen(context)
                                    FourthRakatWomen(context)
                                    NotFirstRakatWomen(context,actions[1])
                                    SecondRakatWomen(context)
                                    ThirdRakatWomen(context)
                                    FourthRakatWomen(context)
                                    NotFirstRakatWomen(context,actions[1])
                                    FourthRakatWomen(context)
                                }
                                FeatureRoutes.PR_Asr.name ->{
                                    FirstRakatWomen(navController,context,actions[0],fajr,zuhr,asr,magrib,isha)
                                    SecondRakatWomen(context)
                                    ThirdRakatWomen(context)
                                    FourthRakatWomen(context)
                                }
                                FeatureRoutes.PR_Magrib.name ->{
                                    FirstRakatWomen(navController,context,actions[0],fajr,zuhr,asr,magrib,isha)
                                    SecondRakatWomen(context)
                                    FourthRakatWomen(context)
                                    NotFirstRakatWomen(context,actions[1])
                                    FourthRakatWomen(context)
                                }
                                FeatureRoutes.PR_Isha.name ->{
                                    FirstRakatWomen(navController,context,actions[0],fajr,zuhr,asr,magrib,isha)
                                    SecondRakatWomen(context)
                                    ThirdRakatWomen(context)
                                    FourthRakatWomen(context)
                                    NotFirstRakatWomen(context,actions[1])
                                    FourthRakatWomen(context)
                                }
                            }

                        }
                        else -> null
                    }
                }
            }
        }
    )
}

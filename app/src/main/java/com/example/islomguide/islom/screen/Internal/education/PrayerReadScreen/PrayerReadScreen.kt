package com.example.islomguide.islom.screen.Internal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.components.ChoiceTimeCard
import com.example.islomguide.core.ui_kit.CommonFeatureScreen
import com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen.PrayerReadVM
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel

@Composable
fun PrayerReadScreen(
    navController: NavController,
    viewModel: PrayerReadVM,
    modifier: Modifier = Modifier,
    viewModel2: PrayerTimeViewModel
){
    var isFirstS by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val choice = context.getString(R.string.choice_MF)
    val gender = context.resources.getStringArray(R.array.gender_choice)

    CommonFeatureScreen({
        if(isFirstS) {
            Column(
                modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .offset(y = (-25).dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    choice,
                    style = MaterialTheme.typography.headlineMedium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Button(
                        onClick = {
                            isFirstS = false
                            viewModel.ChangeMen()
                        }
                    ) {
                        Text(
                            gender[2],
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Spacer(modifier.padding(horizontal = 16.dp))
                    Button(
                        onClick = {
                            isFirstS = false
                            viewModel.ChangeWomen()
                        }
                    ) {
                        Text(
                            gender[3],
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }else{
            ChoiceTimeCard(navController, viewModel2)
        }

    }) { null }
}

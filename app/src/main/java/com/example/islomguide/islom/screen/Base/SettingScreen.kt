package com.example.islomguide.islom.ui.screen.settingScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.ui_kit.CommonBaseScreen
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel

@SuppressLint("NewApi")
@Composable
fun Settings(
    navController: NavController,
    PTViewModel: PrayerTimeViewModel
){
     val context = LocalContext.current
    val choose = context.getString(R.string.choose_city)
    var chooseCity by rememberSaveable { mutableStateOf(false) }
    val cities = context.resources.getStringArray(R.array.cities)
    val current = context.getString(R.string.current)

    CommonBaseScreen(
        navController = navController,
    ){
//        Text("Сбрасывать тасбех при выходе: ")

        Surface(
            onClick = {chooseCity = true},
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .offset(y = 75.dp)
            ,
            color = MaterialTheme.colorScheme.onSecondary
        ){

            Icon(
                imageVector = Icons.Filled.Map,
                contentDescription = null,
                Modifier.offset(x = (-175).dp)
            )
            Text(
                choose,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.offset(x = 75.dp)
            )
            Icon(
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = null,
                Modifier.offset(x = 185.dp)
            )
        }
        if(chooseCity){
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                modifier = Modifier.offset(y = 6.dp)
            ){
                Column{
                    Spacer(Modifier.padding(10.dp))
                    Text("$current : ${PTViewModel.selectedCity}",style = MaterialTheme.typography.headlineMedium)
                    Spacer(Modifier.padding(vertical = 12.dp))
                    cities.forEach { city ->
                        Surface(
                            onClick = {
                                chooseCity = false
                                PTViewModel.setCity(city)
                                PTViewModel.setCountry(context,city)
                            },
                            Modifier
                                .fillMaxWidth(),
                            color = MaterialTheme.colorScheme.surface,

                            ){
                            Text(
                                text = city,
                                style = MaterialTheme.typography.titleLarge
                            )

                            Icon(
                                imageVector = Icons.Rounded.ChevronRight,
                                contentDescription = null,
                                Modifier.offset(x = 185.dp)
                            )

                        }
                    }
                    Spacer(Modifier.padding(vertical = 12.dp))
                }
            }
        }
    }
}

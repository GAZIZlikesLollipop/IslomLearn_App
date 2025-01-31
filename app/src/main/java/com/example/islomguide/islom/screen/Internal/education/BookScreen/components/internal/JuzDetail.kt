package com.example.islomguide.islom.screen.Internal.education.BookScreen.components.internal

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.core.data.model.network.JuzData
import com.example.islomguide.core.ui_kit.CommonFeatureScreen
import com.example.islomguide.core.ui_kit.ErrorScreen
import com.example.islomguide.core.ui_kit.Loading
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.JuzUiState

@Composable
fun JuzDetail(
    navController: NavController,
    viewModel: BookViewModel,
    juzId : Int,
    surId : Int
){
    val uiState = viewModel.juzUiState

    LaunchedEffect(Unit){
        viewModel.fetchJuz(juzId)
    }
    CommonFeatureScreen(
        content = {
            Box {
                when (uiState) {
                    is JuzUiState.Success -> {
                        JuzList(uiState.list, surId)
                    }

                    is JuzUiState.Error -> {
                        ErrorScreen(
                            { viewModel.fetchJuz(juzId) },
                            modifier = Modifier.fillMaxSize().align(alignment = Alignment.Center)
                        )
                    }

                    is JuzUiState.Loading -> {
                       Loading()
                    }
                }
            }
        }
    )
}
@Composable
fun JuzList(
    juzData: List<JuzData?>,
    index: Int
){
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        item{
            Spacer(Modifier.offset(y = 50.dp))
        }
        items(juzData){ juzData ->
            if (juzData != null) {
                val surah = juzData.surahs[index]

                Column {
                    Text(
                        text = "${surah?.englishName}",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(vertical = 12.dp),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    val ayahsForSurah = juzData.ayahs.filter {
                        it.numberInSurah != null && it.numberInSurah <= (surah?.numberOfAyahs ?: 0)
                    }

                    ayahsForSurah.forEach { ayah ->
                        Card(
                            modifier = Modifier
                                .padding(vertical = 2.dp),
                            shape = RoundedCornerShape(0.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${ayah.numberInSurah}. ",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.offset(y = 16.dp)
                                )
                                ayah.text?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.headlineMedium,
                                        textAlign = TextAlign.Right,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

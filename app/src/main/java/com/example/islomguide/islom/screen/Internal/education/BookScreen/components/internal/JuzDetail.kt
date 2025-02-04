package com.example.islomguide.islom.screen.Internal.education.BookScreen.components.internal

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.data.model.network.J_Ayahs
import com.example.islomguide.core.data.model.network.JuzData
import com.example.islomguide.core.data.model.network.Surahs
import com.example.islomguide.core.ui_kit.CommonFeatureScreen
import com.example.islomguide.core.ui_kit.ErrorScreen
import com.example.islomguide.core.ui_kit.Loading
import com.example.islomguide.islom.components.BookTopBar
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.JuzUiState

@SuppressLint("ResourceType")
@Composable
fun JuzDetail(
    navController: NavController,
    viewModel: BookViewModel,
    juzId : Int
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
                        JuzList(uiState.list)
                    }

                    is JuzUiState.Error -> {
                        ErrorScreen(
                            { viewModel.fetchJuz(juzId) },
                            modifier = Modifier
                                .fillMaxSize()
                                .align(alignment = Alignment.Center)
                        )
                    }

                    is JuzUiState.Loading -> {
                        Loading()
                    }
                }
            }
        },
        topAppBar = { BookTopBar(navController, "${stringArrayResource(R.array.book_sections)[1]} $juzId")}
    )
}
@Composable
fun JuzList(juzData: List<JuzData?>) {
    LazyColumn {
        item {
            Spacer(Modifier.offset(y = 50.dp))
        }
        items(juzData) { juz ->
            juz?.ayahs?.forEach { ayah ->

                juz.surahs.forEach { surah ->
                    if(surah.value.number == ayah.surah.number && ayah.numberInSurah == 1){
                        Spacer(Modifier.padding(vertical = 12.dp))
                        Text(
                            text = "${surah.value.englishName}",
                            style = MaterialTheme.typography.headlineLarge,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(Modifier.padding(vertical = 16.dp))
                    }
                }

                Card(
                    modifier = Modifier.padding(vertical = 2.dp),
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

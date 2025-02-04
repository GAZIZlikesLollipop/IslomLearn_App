package com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.data.model.network.JuzData
import com.example.islomguide.core.data.model.network.Surahs
import com.example.islomguide.core.ui_kit.CommonFeatureScreen
import com.example.islomguide.core.ui_kit.ErrorScreen
import com.example.islomguide.core.ui_kit.Loading
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.JuzUiState

@Composable
fun Juz(
    navController: NavController,
    viewModel: BookViewModel
) {

    val uiState = viewModel.juzUiState
    val juzContentLabels = stringArrayResource(R.array.juz_content)

    LaunchedEffect(Unit) {
        viewModel.fetchAllJuz()
    }

    CommonFeatureScreen(
        content = {
            Box {
                Box(Modifier.fillMaxSize()) {
                    when (uiState) {
                        is JuzUiState.Success -> {
                            JuzList(
                                juzList = uiState.list,
                                navController = navController,
                                juzContentLabels = juzContentLabels
                            )
                        }

                        is JuzUiState.Error -> {
                                ErrorScreen(
                                    { viewModel.fetchAllJuz() },
                                    modifier = Modifier.fillMaxSize().align(alignment = Alignment.Center)
                                )
                            }

                        is JuzUiState.Loading -> {
                            Loading()
                        }
                    }
                }
            }
        },
        topAppBar = {null}
    )

}

@Composable
fun JuzList(
    juzList: List<JuzData?>,
    navController: NavController,
    juzContentLabels: Array<String>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 110.dp)
    ) {
        items(juzList) { juz ->

            Card(
                onClick = {
                    navController.navigate("b_juz_dt/${juz?.number}")
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(5.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        text = "${juz?.number} ${juzContentLabels[0]}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    val firstSurah = juz?.surahs?.values?.firstOrNull()
                    Text(
                        text = "${juzContentLabels[1]} ${firstSurah?.englishName} " +
                                "${juz?.ayahs?.get(0)?.numberInSurah} ${juzContentLabels[2]}, " +
                                "${juzContentLabels[3]} ${juz?.ayahs?.size} ${juzContentLabels[2]}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
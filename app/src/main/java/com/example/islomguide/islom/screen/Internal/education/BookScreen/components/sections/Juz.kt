package com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.data.model.network.JuzData
import com.example.islomguide.core.data.model.network.Surahs
import com.example.islomguide.core.ui_kit.ErrorScreen
import com.example.islomguide.islom.components.BookTopBar
import com.example.islomguide.islom.components.CommonFeatureScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.JuzUiState
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.NavTopBar

@Composable
fun Juz(
    navController: NavController,
    viewModel: BookViewModel
) {

    val uiState = viewModel.juzUiState
    val sectionTitle = stringArrayResource(R.array.inrernal_sections)[1]
    val juzContentLabels = stringArrayResource(R.array.juz_content)

    LaunchedEffect(Unit) {
        viewModel.fetchAllJuz()
    }

    CommonFeatureScreen(
        navController = navController,
        topAppBar = { BookTopBar(sectionTitle, navController) },
        content = {
            Box {
                NavTopBar(navController)
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
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(82.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
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
            .padding(top = 50.dp)
    ) {
        items(juzList) { juz ->
            var isExpanded by rememberSaveable { mutableStateOf(false) }

            Card(
                onClick = { isExpanded = !isExpanded },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(8.dp)
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
                    AnimatedVisibility(visible = isExpanded) {
                        Column {
                            juz?.surahs?.values?.forEach { surah ->
                                JuzCard(surah) {
                                    navController.navigate("b_juz_dt/${juz.number}/${surah.number}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun JuzCard(
    surah: Surahs,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(4.dp),
        onClick = onClick
    ) {
        Text(
            text = "${surah.number}. ${surah.englishName}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(8.dp)
        )
    }
}

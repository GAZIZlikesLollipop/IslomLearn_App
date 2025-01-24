package com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.data.model.network.JuzData
import com.example.islomguide.core.data.model.network.Surahs
import com.example.islomguide.islom.components.BookTopBar
import com.example.islomguide.islom.components.CommonFeatureScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.JuzUiState
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.NavTopBar

@Composable
fun Juz(
    navController: NavController,
    viewModel: BookViewModel
){
    val uiState = viewModel.juzUiState
    val sec = stringArrayResource(R.array.inrernal_sections)[1]
    val again = stringResource(R.string.try_again)
    val error = stringResource(R.string.error)

    LaunchedEffect(Unit) {
        viewModel.fetchAllJuz()
    }
    CommonFeatureScreen(
        navController = navController,
        topAppBar = { BookTopBar(sec,navController) },
        content = {
            NavTopBar(navController)
            Box {
                when (uiState) {
                    is JuzUiState.Success -> {

                        LazyColumn(
                            Modifier.fillMaxSize().padding(top = 50.dp)
                        ) {

                            uiState.list.forEachIndexed { index, juzData ->
                                item {
                                    juzData?.let {
                                        JuzCard(
                                            it,
                                            index,
                                        ) { navController.navigate("b_juz_dt/${index + 1}") }
                                    }
                                    Spacer(Modifier.padding(vertical = 1.dp))
                                }
                            }
                        }
                    }

                    is JuzUiState.Error -> {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(y = 150.dp)) {
                            Text(
                                error,
                                style = MaterialTheme.typography.headlineLarge,
                                color = MaterialTheme.colorScheme.onError,
                                modifier = Modifier.offset(x = 65.dp)
                            )
                            Spacer(Modifier.padding(vertical = 36.dp))
                            Button(
                                onClick = { viewModel.fetchAllJuz() },
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.errorContainer),
                                modifier = Modifier
                                    .height(75.dp)
                                    .offset(x = 70.dp)

                            ) {
                                Text(
                                    again,
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }

                    is JuzUiState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(82.dp)
                                .align(Alignment.Center)
                                .offset(y = 300.dp,x = 150.dp)
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun JuzCard(
    juz: JuzData?,
    index: Int,
    onClick: () -> Unit
){
    val utils = stringArrayResource(R.array.juz_content)
    Card(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(0.dp)
    ){

        Column(Modifier.fillMaxSize().padding(16.dp)){
            if (juz != null) {
                Text(
                    "${juz.number} ${utils[0]}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            if (juz != null) {
                val firstSurah = juz.surahs.values.firstOrNull()
                Text(
                    "${utils[1]} ${firstSurah?.englishName}, ${juz.ayahs[0].numberInSurah} ${utils[2]}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

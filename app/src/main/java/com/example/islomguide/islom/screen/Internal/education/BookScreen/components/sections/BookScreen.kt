package com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.data.model.network.SurahsData
import com.example.islomguide.core.ui_kit.CommonInternalScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookUiState
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.components.NavTopBar

@Composable
fun BookScreen(
    navController: NavController,
    viewModel: BookViewModel,
){

    val surahUiState = viewModel.bookUiState
    val again = stringResource(R.string.try_again)
    val error = stringResource(R.string.error)

    LaunchedEffect(Unit) {
        viewModel.fetchAllSurahs()
    }

    CommonInternalScreen(
        navController
    ) {
        Box {
            NavTopBar(navController)
            when (surahUiState) {
                is BookUiState.Error -> {
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
                            onClick = { viewModel.fetchAllSurahs() },
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

                is BookUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(82.dp)
                            .align(Alignment.Center)
                            .offset(y = 150.dp)
                    )
                }

                is BookUiState.Success -> {
                    (surahUiState).list.let {
                        LazyColumn(
                            Modifier
                                .fillMaxSize()
                                .padding(top = 50.dp)
                        ) {
                            it.forEachIndexed { _, surahs ->
                                item {
                                    if (surahs != null) {
                                        Success(surahs,navController
                                        ) { navController.navigate("b_book_dt/${surahs.number?.minus(
                                            1
                                        )}/${surahs.englishName}") }
                                    }

                                    Spacer(Modifier.padding(vertical = 1.dp))
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
fun Success(
    surahs: SurahsData,
    navController: NavController,
    onClick : () -> Unit
){
    val context = LocalContext.current
    val ayat = context.getString(R.string.ayat)
    Box {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable {
                    onClick()
                },
            shape = RoundedCornerShape(0.dp)
        ) {
            Row {
                surahs.number?.let {
                    Text(
                        "$it.",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .offset(y = (8).dp),
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
                Column(modifier = Modifier
                    .weight(2f)
                    .offset(y = (7).dp)) {

                    surahs.englishName?.let {
                        Text(
                            it,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                        Text(
                            "${surahs.revelationType} ${surahs.numberOfAyahs} $ayat",
                            style = MaterialTheme.typography.titleMedium
                        )
                }
                surahs.name?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.offset(x = (-12).dp, y = 16.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

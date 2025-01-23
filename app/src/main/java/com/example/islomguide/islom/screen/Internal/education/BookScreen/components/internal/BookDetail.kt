package com.example.islomguide.islom.screen.Internal.education.BookScreen.components.internal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.data.model.network.S_Ayahs
import com.example.islomguide.islom.components.CommonFeatureScreen
import com.example.islomguide.islom.components.BookTopBar
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookUiState
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel

@Composable
fun BookDetail(
    navController: NavController,
    viewModel: BookViewModel,
    surahId: Int,
    name : String
){
    val uiState = viewModel.bookUiState

    val again = stringResource(R.string.try_again)
    val error = stringResource(R.string.error)

    CommonFeatureScreen(
        navController,
        topAppBar = { BookTopBar(name, navController)},
        content = {
                when (uiState) {
                    is BookUiState.Success -> {
                        val ayahs = uiState.list[surahId]!!.ayahs

                        LazyColumn{

                            item{
                                Spacer(Modifier.padding(vertical = 12.dp))
                            }

                            items(ayahs) { item ->
                                Success(item)
                                Spacer(Modifier.padding(vertical = 1.dp))
                            }

                        }

                    }

                    is BookUiState.Error -> {
                        Column(
                            modifier = Modifier
                                .offset(y = 150.dp)
                        ) {
                            Text(
                                error,
                                style = MaterialTheme.typography.headlineLarge,
                                color = MaterialTheme.colorScheme.onError,
                                modifier = Modifier.offset(x = 65.dp)
                            )
                            Spacer(Modifier.padding(vertical = 36.dp))
                            Button(
                                onClick = {
                                    viewModel.fetchSurahs(surahId)
                                },
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
                                .offset(y = 150.dp)
                        )
                    }
                }
        }
    )
}
@Composable
fun Success(
    SAyahs: S_Ayahs?
) {
    Card(
        Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(0.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start, // Выравнивание содержимого Row по левому краю
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth() // Row занимает всю ширину
                .padding(16.dp)
        ) {
            // Номер слева
            Text(
                "${SAyahs?.numberInSurah}.",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Start // Выравнивание текста слева
            )

            Spacer(Modifier.weight(1f)) // Занимает оставшееся пространство

            // Текст справа
            Text(
                "${SAyahs?.text}",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge
            )
        }


    }
}


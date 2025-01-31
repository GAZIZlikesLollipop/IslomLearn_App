package com.example.islomguide.islom.screen.Internal.education.BookScreen.components.internal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.islomguide.core.data.model.network.QuranAyahs
import com.example.islomguide.core.data.model.network.QuranSurahs
import com.example.islomguide.core.ui_kit.CommonFeatureScreen
import com.example.islomguide.core.ui_kit.ErrorScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookUiState
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import androidx.compose.foundation.lazy.items
import com.example.islomguide.core.ui_kit.Loading

@Composable
fun BookDetail(
    viewModel: BookViewModel,
    surahId: Int
){
    val uiState = viewModel.bookUiState

    CommonFeatureScreen(
        content = {
            Box {
                when (uiState) {
                    is BookUiState.Success -> {
                        DetailContent(uiState.list?.surahs?.get(surahId))
                    }

                    is BookUiState.Error -> {
                        ErrorScreen(
                            { viewModel.fetchBookContent() },
                            modifier = Modifier.fillMaxSize().align(alignment = Alignment.Center)
                        )
                    }

                    is BookUiState.Loading -> {
                        Loading()
                    }
                }
            }
        }
    )
}
@Composable
fun DetailContent(
    surahs: QuranSurahs?
) {
    LazyColumn{
        item {
            Spacer(Modifier.padding(vertical = 12.dp))
        }
        if (surahs != null) {
            items(surahs.ayahs){surahs ->
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
                            "${surahs?.numberInSurah}.",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Start // Выравнивание текста слева
                        )

                        Spacer(Modifier.weight(1f)) // Занимает оставшееся пространство

                        // Текст справа
                        Text(
                            "${surahs?.text}",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                }
                Spacer(Modifier.padding(vertical = 1.dp))
            }
        }
    }
}

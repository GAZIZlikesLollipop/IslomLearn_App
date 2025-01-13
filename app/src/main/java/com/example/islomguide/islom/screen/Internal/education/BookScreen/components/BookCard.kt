package com.example.islomguide.islom.screen.Internal.education.BookScreen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookUiState
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel

@SuppressLint("Range")
@Composable
fun BookCard(
    viewModel: BookViewModel,
    num : Int
){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val uiState = viewModel.bookUiState
        LaunchedEffect(Unit) {
            viewModel.getSurah(num)
        }
        when (uiState) {
            is BookUiState.Success -> {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row {
                        Text(
                            "$num",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.weight(1f)
                        )
                        Column(modifier = Modifier.weight(2f)) {


                            Text(
                                "${uiState.list?.englishName!![num]}. ",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                "${uiState.list.revelationType!![num]}  ${uiState.list.numberOfAyahs}",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        Text(
                            "${uiState.list?.name!![1]}",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.secondary,
                        thickness = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }

            is BookUiState.Error -> {
                Button(
                    onClick = {viewModel.getSurah(num)},
                    modifier = Modifier.size(100.dp)
                ){
                    Text("errir")
                }
            }
            is BookUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}
package com.example.islomguide.islom.screen.Internal.education.BookScreen.components.sections

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.data.model.network.QuranData
import com.example.islomguide.core.ui_kit.CommonFeatureScreen
import com.example.islomguide.core.ui_kit.ErrorScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookUiState
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import androidx.compose.foundation.lazy.items
import com.example.islomguide.core.ui_kit.Loading

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun BookScreen(
    navController: NavController,
    viewModel: BookViewModel,
){
    val uiState = viewModel.bookUiState
    LaunchedEffect(Unit) {
        viewModel.fetchBookContent()
    }
    CommonFeatureScreen(
        content = {
            Box {
                AnimatedContent(
                    uiState,
                    transitionSpec = {
                        fadeIn(
                            animationSpec = tween(1500)
                        ) togetherWith fadeOut(animationSpec = tween(800))
                    },
                ) {
                    when (uiState) {
                        is BookUiState.Error -> {
                            ErrorScreen(
                                { viewModel.fetchBookContent() }, modifier = Modifier
                                    .fillMaxSize()
                                    .align(alignment = Alignment.Center)
                            )
                        }

                        is BookUiState.Loading -> {
                            Loading()
                        }

                        is BookUiState.Success -> {
                            BookContent(uiState.list, navController)
                        }
                    }
                }
            }
        },

    ) { null }
}
@Composable
fun BookContent(
    content: QuranData?,
    navController: NavController
) {
    val ayat = stringResource(R.string.ayat)

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 108.dp)
    ) {
        if (content != null) {
            items(content.surahs) { surahs ->
                Box {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(3.dp)
                            .clickable {
                                navController.navigate(
                                    "b_book_dt/${surahs?.number?.minus(1)}"
                                )
                            },
                    ) {
                        Row {
                            Text(
                                "${surahs?.number}.",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                                    .offset(y = (8).dp),
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Column(
                                modifier = Modifier
                                    .weight(2f)
                                    .offset(y = (7).dp)
                            ) {

                                surahs?.englishName?.let {
                                    Text(
                                        it,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                                Text(
                                    "${surahs?.revelationType} ${surahs?.ayahs?.size} $ayat",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                            surahs?.name?.let {
                                Text(
                                    "${it}\n",
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier.offset(x = (-12).dp, y = 16.dp),
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
package com.example.islomguide.islom.screen.Internal.education.BookScreen.components.internal

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.data.model.network.JuzData
import com.example.islomguide.islom.components.BookTopBar
import com.example.islomguide.islom.components.CommonFeatureScreen
import com.example.islomguide.islom.screen.Internal.education.BookScreen.BookViewModel
import com.example.islomguide.islom.screen.Internal.education.BookScreen.JuzUiState

@Composable
fun JuzDetail(
    navController: NavController,
    viewModel: BookViewModel,
    juzId : Int
){
    val uiState = viewModel.juzUiState
    val again = stringResource(R.string.try_again)
    val error = stringResource(R.string.error)
    val juzCon = stringArrayResource(R.array.juz_content)[0]

    LaunchedEffect(Unit){
        viewModel.fetchJuz(juzId)
    }
    CommonFeatureScreen(
        navController = navController,
        topAppBar = { BookTopBar("$juzId $juzCon", navController) },
        content = {
            when(uiState){
                is JuzUiState.Success -> {
                    LazyColumn(
                        Modifier.fillMaxSize()
                    ) {
                        item{
                            Spacer(Modifier.padding(vertical = 12.dp))
                        }
                        items(uiState.list){juzData ->
                            if (juzData != null) {
                                val surahList = juzData.surahs.values.sortedBy { it.numberOfAyahs }

                                Column {
                                    surahList.forEach { surah ->
                                        // Заголовок суры
                                        surah.englishName?.let {
                                            Text(
                                                text = it,
                                                style = MaterialTheme.typography.headlineLarge,
                                                modifier = Modifier.padding(vertical = 12.dp),
                                                textAlign = TextAlign.Start,
                                                color = MaterialTheme.colorScheme.secondary
                                            )
                                        }

                                        // Список аятов, относящихся к этой суре
                                        val ayahsForSurah = juzData.ayahs.filter {
                                            it.numberInSurah != null && it.numberInSurah <= (surah.numberOfAyahs
                                                ?: 0)
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

                }
                is JuzUiState.Error -> {
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
                                viewModel.fetchJuz(juzId)
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
                is JuzUiState.Loading -> {
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
fun JuzContent(juzData: JuzData?) {
    if (juzData != null) {
        val surahList = juzData.surahs.values.sortedBy { it.numberOfAyahs }

        Column{
            surahList.forEach { surah ->
                // Заголовок суры
                    surah.englishName?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.padding(vertical = 12.dp),
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }

                // Список аятов, относящихся к этой суре
                val ayahsForSurah = juzData.ayahs.filter { it.numberInSurah != null && it.numberInSurah <= (surah.numberOfAyahs ?: 0) }

                ayahsForSurah.forEach{ ayah ->
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

//Me
//@Composable
//fun JuzContent(
//    juzData: JuzData?
//){
//    if (juzData == null) return
//    Column {
//
//        juzData.surahs.forEach { surah ->
//
//            Text(
//                "${surah.value.englishName}",
//                style = MaterialTheme.typography.headlineMedium,
//                color = MaterialTheme.colorScheme.tertiary
//            )
//            val surahByAyahs = juzData.ayahs.filter { it.numberInSurah!! in 1..surah.value.numberOfAyahs!! }
//            surahByAyahs.forEach { ayah ->
//
//                Card(
//                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
//                    shape = RoundedCornerShape(0.dp),
//                ) {
//                    Row(
//                        Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp), // Отступы для Row
//                        horizontalArrangement = Arrangement.SpaceBetween, // Разделение между элементами
//                        verticalAlignment = Alignment.CenterVertically, // Выравнивание по вертикали
//                    ) {
//                        // Номер слева
//                        Text(
//                            text = "${ayah.numberInSurah}",
//                            textAlign = TextAlign.Start, // Выравнивание номера влево
//                            style = MaterialTheme.typography.titleMedium,
//                            color = MaterialTheme.colorScheme.onTertiary
//                        )
//
//                        // Текст справа
//                        Text(
//                            text = "${ayah.text}",
//                            textAlign = TextAlign.End, // Выравнивание текста вправо
//                            style = MaterialTheme.typography.titleLarge,
//                            modifier = Modifier.weight(1f), // Занимает оставшееся пространство
//                            color = MaterialTheme.colorScheme.primary
//                        )
//                    }
//                }
//            }
//        }
//    }
//}

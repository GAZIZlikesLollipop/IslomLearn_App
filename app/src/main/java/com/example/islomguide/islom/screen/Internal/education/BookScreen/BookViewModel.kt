package com.example.islomguide.islom.screen.Internal.education.BookScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islomguide.core.data.model.network.JuzData
import com.example.islomguide.core.data.model.network.SurahsData
import com.example.islomguide.core.data.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Состояние для Ayah
sealed interface BookUiState {
    data class Success(val list: List<SurahsData?>) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}

sealed interface JuzUiState{
    data class Success(val list: List<JuzData?>) : JuzUiState
    object Error : JuzUiState
    object Loading : JuzUiState
}

class BookViewModel(private val bookRepository: BookRepository): ViewModel() {

    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set
    var juzUiState: JuzUiState by mutableStateOf(JuzUiState.Loading)
        private set

    fun fetchSurahs(surahId : Int) {
        viewModelScope.launch {
            bookUiState = try {
                BookUiState.Success(listOf(bookRepository.getSurahs(surahId)))
            } catch (e: IOException) {
                BookUiState.Error
            } catch (e: HttpException) {
                BookUiState.Error
            }
        }
    }

    fun fetchAllSurahs() {
        viewModelScope.launch(Dispatchers.IO) {
            bookUiState = try {
                val tempList = mutableListOf<SurahsData?>()
                for (id in 1..114) { // Итерируем по всем сурам
                    val surah = bookRepository.getSurahs(id)
                    surah.let { tempList.addAll(listOf(it)) } // Добавляем только ненулевые данные
                } // Обновляем список сур
                BookUiState.Success(tempList)
            } catch (e: IOException) {
                Log.e("BookViewModel", "Network Error: ${e.message}")
                BookUiState.Error

            } catch (e: HttpException) {
                Log.e("BookViewModel", "HTTP Error: ${e.message}")
                BookUiState.Error
            }
        }
    }

    fun fetchJuz(juzId: Int){
        viewModelScope.launch {
            juzUiState =try {
                JuzUiState.Success(listOf(bookRepository.getJuz(juzId)))
            }catch(e : IOException){
                JuzUiState.Error
            }catch (e : HttpException){
                JuzUiState.Error
            }
        }
    }

    fun fetchAllJuz() {
        viewModelScope.launch {
            juzUiState = try {
                val tempList = mutableListOf<JuzData?>()
                for (id in 1..30) { // Итерируем по всем сурам
                    val surah = bookRepository.getJuz(id)
                    surah.let { tempList.addAll(listOf(it)) } // Добавляем только ненулевые данные
                } // Обновляем список сур
                JuzUiState.Success(tempList)
            } catch (e: IOException) {
                Log.e("BookViewModel", "Network Error: ${e.message}")
                JuzUiState.Error

            } catch (e: HttpException) {
                Log.e("BookViewModel", "HTTP Error: ${e.message}")
                JuzUiState.Error
            }
        }
    }
}

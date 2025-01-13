package com.example.islomguide.islom.screen.Internal.education.BookScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islomguide.core.data.model.network.Surah
import com.example.islomguide.core.data.repository.BookRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookUiState {
    data class Success(val list: Surah?) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}

class BookViewModel(private val bookRepository: BookRepository): ViewModel() {

    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    fun getSurah(num: Int) {
        viewModelScope.launch {
            bookUiState = try {
                BookUiState.Success(bookRepository.getBookContent(num))
            }catch (e: IOException){
                BookUiState.Error
            }catch (e: HttpException){
                BookUiState.Error
            }catch (e: Exception) {
                BookUiState.Error
            }
        }
    }
}
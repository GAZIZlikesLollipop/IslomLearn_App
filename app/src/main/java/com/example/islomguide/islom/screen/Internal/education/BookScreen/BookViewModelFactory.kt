package com.example.islomguide.islom.screen.Internal.education.BookScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.islomguide.core.data.repository.BookRepository
import com.example.islomguide.islom.screen.Internal.home.PrayerTime.PrayerTimeViewModel

class BookViewModelFactory(
    private val bookRepository: BookRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(bookRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    
}
package com.example.islomguide.islom.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.islomguide.core.ui_kit.FastText

object TextComponents{
    @Composable
    fun ArticleHeader(text : String){
        FastText(
            text = text,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
            paddingValues = PaddingValues(vertical = 6.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
    }
    @Composable
    fun ArticleSubheader(text: String){
        FastText(
            text = text,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.tertiary,
            paddingValues = PaddingValues(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
    }
    @Composable
    fun ArticleParagraph(text: String){
        FastText(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            paddingValues = PaddingValues(vertical = 10.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
    }

}
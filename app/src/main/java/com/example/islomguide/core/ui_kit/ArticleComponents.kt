package com.example.islomguide.core.ui_kit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun FastText(
    style : TextStyle,
    paddingValues: PaddingValues,
    text : String,
    color : Color
){
    Text(
        text,
        style = style,
        color = color ,
        modifier = Modifier.padding(paddingValues)
    )
}

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
package com.example.islomguide.core.ui_kit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

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
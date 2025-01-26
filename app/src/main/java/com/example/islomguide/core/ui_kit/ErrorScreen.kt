package com.example.islomguide.core.ui_kit

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.islomguide.R

@Composable
fun ErrorScreen( onRetry: () -> Unit, modifier: Modifier = Modifier) {
    val retryLabel = stringResource(R.string.try_again)
    val errorMessage = stringResource(R.string.error)

    Box(

    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center, // Центрируем по вертикали
            horizontalAlignment = Alignment.CenterHorizontally // Центрируем по горизонтали,
        ) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.offset(x = 35.dp)
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.errorContainer)
            ) {
                Text(
                    text = retryLabel,
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

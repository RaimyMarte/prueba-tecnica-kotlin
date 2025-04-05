package com.example.prueba_tecnica_popular.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun LoadingIndicator() {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            Modifier.align(Alignment.Center),
            color = Color(0xFF003262)
        )
    }
}
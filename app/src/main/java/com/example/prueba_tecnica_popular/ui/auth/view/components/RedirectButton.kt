package com.example.prueba_tecnica_popular.ui.auth.view.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun RedirectButton(modifier: Modifier, navigateScreen: () -> Unit, text:String) {
    Text(
        text = text,
        modifier = modifier.clickable { navigateScreen() },
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF003262)
    )
}
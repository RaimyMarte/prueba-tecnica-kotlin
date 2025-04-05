package com.example.prueba_tecnica_popular.ui.auth.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EmailField(
    email: String,
    onTextFieldChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "email@example.com") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    text = errorMessage,
                    color = Color.Red
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF003262),
            unfocusedTextColor = Color(0xFF003262),
            cursorColor = Color(0xFF003262),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
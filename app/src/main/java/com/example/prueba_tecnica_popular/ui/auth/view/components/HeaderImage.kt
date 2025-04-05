package com.example.prueba_tecnica_popular.ui.auth.view.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun HeaderImage(modifier: Modifier, imageId: Int) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = "logo",
        modifier = modifier
    )
}
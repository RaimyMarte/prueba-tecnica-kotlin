package com.example.prueba_tecnica_popular

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.prueba_tecnica_popular.ui.theme.PruebatecnicapopularTheme
import com.example.prueba_tecnica_popular.core.navigation.NavigationWrapper
import com.example.prueba_tecnica_popular.ui.session.viewmodel.AppSessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val appSessionViewModel: AppSessionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebatecnicapopularTheme {
                NavigationWrapper(appSessionViewModel)
            }
        }
    }
}
package com.example.prueba_tecnica_popular

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.prueba_tecnica_popular.ui.theme.PruebatecnicapopularTheme
import androidx.activity.viewModels
import com.example.prueba_tecnica_popular.ui.auth.viewmodel.LoginViewModel
import com.example.prueba_tecnica_popular.ui.users.view.UsersListScreen
import com.example.prueba_tecnica_popular.ui.users.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val usersViewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebatecnicapopularTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
//                    LoginScreen(loginViewModel)
                    UsersListScreen(usersViewModel, onUserClick = { user ->
                        // Navigate to chat screen with this user
                        // For example: navController.navigate("chat/${user.id}")
                    })
                }
            }
        }
    }
}
package com.example.prueba_tecnica_popular.ui.auth.view


import androidx.compose.runtime.Composable
import com.example.prueba_tecnica_popular.ui.auth.viewmodel.LoginViewModel
import com.example.prueba_tecnica_popular.R

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    BaseAuthScreen(
        titleText = "Iniciar Sesión",
        submitLabel = "Iniciar Sesión",
        redirectText = "¿No tienes una cuenta? Regístrate",
        successMessage = "Inicio de sesión exitoso",
        viewModel = viewModel,
        onSubmit = { viewModel.onLoginSelected() },
        navigateToHome = navigateToHome,
        navigateToRedirect = navigateToSignUp,
        showForgotPassword = true,
        imageId = R.drawable.logo
    )
}







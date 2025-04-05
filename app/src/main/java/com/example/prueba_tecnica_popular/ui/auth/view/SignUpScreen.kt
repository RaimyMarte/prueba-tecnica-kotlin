package com.example.prueba_tecnica_popular.ui.auth.view

import androidx.compose.runtime.Composable
import com.example.prueba_tecnica_popular.ui.auth.viewmodel.SignUpViewModel
import com.example.prueba_tecnica_popular.R

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit
) {
    BaseAuthScreen(
        titleText = "Crear Cuenta",
        submitLabel = "Crear Cuenta",
        redirectText = "¿Ya tienes una cuenta? Inicia Sesión",
        successMessage = "La cuenta ha sido creada de manera exitosa",
        viewModel = viewModel,
        onSubmit = { viewModel.onSignUpSelected() },
        navigateToHome = navigateToHome,
        navigateToRedirect = navigateToLogin,
        showForgotPassword = false,
        imageId = R.drawable.team
    )
}







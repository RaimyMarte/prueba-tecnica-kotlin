package com.example.prueba_tecnica_popular.ui.auth.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba_tecnica_popular.ui.auth.view.components.EmailField
import com.example.prueba_tecnica_popular.ui.auth.view.components.ForgotPassword
import com.example.prueba_tecnica_popular.ui.auth.view.components.HeaderImage
import com.example.prueba_tecnica_popular.ui.auth.view.components.PasswordField
import com.example.prueba_tecnica_popular.ui.auth.view.components.RedirectButton
import com.example.prueba_tecnica_popular.ui.auth.view.components.SubmitButton
import com.example.prueba_tecnica_popular.ui.auth.viewmodel.AuthBaseViewModel
import com.example.prueba_tecnica_popular.ui.components.LoadingIndicator
import kotlinx.coroutines.launch

@Composable
fun BaseAuthScreen(
    titleText: String,
    submitLabel: String,
    redirectText: String,
    successMessage: String,
    viewModel: AuthBaseViewModel,
    onSubmit: suspend () -> Unit,
    navigateToHome: () -> Unit,
    navigateToRedirect: () -> Unit,
    showForgotPassword: Boolean = false
) {
    Box(
        (Modifier
            .fillMaxSize()
            .padding(16.dp))
    ) {
        BaseAuthForm(
            modifier = Modifier.align(Alignment.Center),
            titleText = titleText,
            submitLabel = submitLabel,
            redirectText = redirectText,
            successMessage = successMessage,
            viewModel = viewModel,
            onSubmit = onSubmit,
            navigateToHome = navigateToHome,
            navigateToRedirect = navigateToRedirect,
            showForgotPassword = showForgotPassword
        )
    }
}

@Composable
fun BaseAuthForm(
    modifier: Modifier,
    titleText: String,
    submitLabel: String,
    redirectText: String,
    successMessage: String,
    viewModel: AuthBaseViewModel,
    onSubmit: suspend () -> Unit,
    navigateToHome: () -> Unit,
    navigateToRedirect: () -> Unit,
    showForgotPassword: Boolean = false
) {
    val email = viewModel.email.observeAsState(initial = "")
    val password = viewModel.password.observeAsState(initial = "")
    val submitEnabled = viewModel.submitEnabled.observeAsState(initial = false)
    val isLoading = viewModel.isLoading.observeAsState(initial = false)
    val success = viewModel.success.observeAsState(initial = false)
    val error = viewModel.error.observeAsState(initial = null)
    val emailError = viewModel.emailError.observeAsState(initial = null)
    val passwordError = viewModel.passwordError.observeAsState(initial = null)


    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    if (isLoading.value) {
        Box(Modifier.fillMaxSize()) {
            LoadingIndicator()
        }
    } else {
        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = titleText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF003262),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(16.dp))

            EmailField(
                email = email.value ?: "",
                onTextFieldChange = { viewModel.onInputChanged(it, password.value)},
                isError = emailError.value != null,
                errorMessage = emailError.value ?: ""
            )

            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(
                password = password.value ?: "",
                onTextFieldChange = { viewModel.onInputChanged(email.value ?: "", it) },
                isError = passwordError.value != null,
                errorMessage = passwordError.value ?: ""
            )

            if (showForgotPassword) {
                Spacer(modifier = Modifier.padding(6.dp))
                ForgotPassword(Modifier.align(Alignment.End))
            }

            Spacer(modifier = Modifier.padding(10.dp))
            SubmitButton(
                enabled = submitEnabled.value,
                label = submitLabel,
                onClick = {
                    coroutineScope.launch {
                        onSubmit()
                    }
                }
            )

            Spacer(modifier = Modifier.padding(12.dp))
            RedirectButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                navigateScreen = navigateToRedirect,
                text = redirectText
            )

            error.value?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }

            if (success.value) {
                Toast.makeText(context, successMessage, Toast.LENGTH_LONG).show()
                navigateToHome()
            }
        }
    }
}
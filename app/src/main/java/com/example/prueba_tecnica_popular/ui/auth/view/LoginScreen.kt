package com.example.prueba_tecnica_popular.ui.auth.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba_tecnica_popular.R
import com.example.prueba_tecnica_popular.ui.auth.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Box(
        (Modifier
            .fillMaxSize()
            .padding(16.dp))
    ) {
        Login(Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {
    val email = viewModel.email.observeAsState(initial = "")
    val password = viewModel.password.observeAsState(initial = "")
    val loginEnabled = viewModel.loginEnabled.observeAsState(initial = true)
    val isLoading = viewModel.isLoading.observeAsState(initial = false)
    val success = viewModel.success.observeAsState(initial = false)
    val error = viewModel.error.observeAsState(initial = null)

    val coroutineScope = rememberCoroutineScope()

    if (isLoading.value) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                Modifier.align(Alignment.Center),
                color = Color(0xFF003262)
            )
        }
    } else {
        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email.value) { viewModel.onLoginChanged(it, password.value) }

            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password.value) { viewModel.onLoginChanged(email.value, it) }

            Spacer(modifier = Modifier.padding(6.dp))
            ForgotPassword(Modifier.align(Alignment.End))

            Spacer(modifier = Modifier.padding(10.dp))
            LoginButton(
                loginEnabled = loginEnabled.value,
                onLoginSelected = {
                    coroutineScope.launch {
                        viewModel.onLoginSelected()
                    }
                }
            )

            Spacer(modifier = Modifier.padding(12.dp))
            SignUpButton(Modifier.align(Alignment.CenterHorizontally))

            error.value?.let {
                Toast.makeText(LocalContext.current, it, Toast.LENGTH_LONG).show()
            }

            if (success.value) {
                Toast.makeText(LocalContext.current, "Inicio de sesión exitoso", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}

@Composable
fun SignUpButton(modifier: Modifier) {
    Text(
        text = "¿No tienes una cuenta? Regístrate",
        modifier = modifier.clickable { },
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF003262)
    )
}

@Composable
fun LoginButton(loginEnabled: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF003262),
            disabledContainerColor = Color(0xFFB0BFC9)
        ),
        enabled = loginEnabled
    ) {
        Text(text = "Iniciar Sesión")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "¿Olvidaste tu contraseña?",
        modifier = modifier.clickable { },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF003262)
    )
}

@Composable
fun EmailField(email: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "example@example.com") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF003262),
            unfocusedTextColor = Color(0xFF003262),
            cursorColor = Color(0xFF003262),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF003262),
            unfocusedTextColor = Color(0xFF003262),
            cursorColor = Color(0xFF003262),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "logo",
        modifier = modifier
    )
}


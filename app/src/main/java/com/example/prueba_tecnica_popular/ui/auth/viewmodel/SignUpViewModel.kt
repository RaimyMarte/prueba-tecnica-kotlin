package com.example.prueba_tecnica_popular.ui.auth.viewmodel

import com.example.prueba_tecnica_popular.domain.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : AuthBaseViewModel() {

    override val passwordValidator: (String) -> Boolean = { pwd ->
        pwd.length >= 8 &&
                pwd.any { it.isUpperCase() } &&
                pwd.any { it.isLowerCase() } &&
                pwd.any { it.isDigit() }
    }

    override val passwordErrorMessage: (String) -> String? = { pwd ->
        when {
            pwd.isEmpty() -> null
            pwd.length < 8 -> "La contraseña debe tener al menos 8 caracteres"
            !pwd.any { it.isUpperCase() } -> "La contraseña debe tener al menos una mayúscula"
            !pwd.any { it.isLowerCase() } -> "La contraseña debe tener al menos una minúscula"
            !pwd.any { it.isDigit() } -> "La contraseña debe tener al menos un número"
            else -> null
        }
    }


    fun onSignUpSelected() {
        submitAuthAction {
            registerUseCase(
                _email.value ?: "",
                _password.value ?: ""
            )
        }
    }
}
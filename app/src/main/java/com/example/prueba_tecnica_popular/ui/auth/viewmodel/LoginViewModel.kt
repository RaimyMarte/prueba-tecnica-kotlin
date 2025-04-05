package com.example.prueba_tecnica_popular.ui.auth.viewmodel

import com.example.prueba_tecnica_popular.domain.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : AuthBaseViewModel() {

    override val passwordValidator: (String) -> Boolean = { it.isNotEmpty() }

    override val passwordErrorMessage: (String) -> String? = { pwd ->
        when {
            pwd.isEmpty() -> null
            else -> null
        }
    }


    fun onLoginSelected() {
        submitAuthAction {
            loginUseCase(
                _email.value ?: "",
                _password.value ?: ""
            )
        }
    }
}
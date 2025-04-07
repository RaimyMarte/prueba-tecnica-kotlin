package com.example.prueba_tecnica_popular.ui.auth.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.prueba_tecnica_popular.data.api.ApiResult

abstract class AuthBaseViewModel:ViewModel() {
    protected val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    protected val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    protected val _submitEnabled = MutableLiveData<Boolean>()
    val submitEnabled: LiveData<Boolean> = _submitEnabled

    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    protected val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    protected val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    protected val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    protected val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    protected abstract val passwordValidator: (String) -> Boolean
    protected abstract val passwordErrorMessage: (String) -> String?

    fun onInputChanged(email: String, password: String) {
        _email.value = email
        _password.value = password

        val isEmailValid = isValidEmail(email)
        _emailError.value = if (!isEmailValid && email.isNotEmpty()) "Email no válido" else null

        val isPasswordValid = isValidPassword(password)
        _passwordError.value = if (!isPasswordValid && password.isNotEmpty())
            passwordErrorMessage(password) else null

        _submitEnabled.value = isEmailValid && isPasswordValid
    }


    protected fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    protected fun isValidPassword(password: String): Boolean = passwordValidator(password)

    protected fun submitAuthAction(action: suspend () -> ApiResult<*>) {
        viewModelScope.launch {
            _isLoading.value = true

            when (val result = action()) {
                is ApiResult.Success -> {
                    _success.value = true
                    _error.value = null
                }

                is ApiResult.Error -> {
                    _success.value = false
                    _error.value = result.message
                }
            }

            _isLoading.value = false
        }
    }
}
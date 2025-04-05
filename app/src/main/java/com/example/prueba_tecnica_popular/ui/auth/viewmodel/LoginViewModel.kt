package com.example.prueba_tecnica_popular.ui.auth.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba_tecnica_popular.data.api.ApiResult
import com.example.prueba_tecnica_popular.domain.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {


    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnabled = MutableLiveData<Boolean>()
    val loginEnabled: LiveData<Boolean> = _loginEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password

        _loginEnabled.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length >= 6


    fun onLoginSelected() {
        viewModelScope.launch {
            _isLoading.value = true

            val result = loginUseCase(
                _email.value ?: "",
                _password.value ?: ""
            )

            when (result) {
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
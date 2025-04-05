package com.example.prueba_tecnica_popular.domain.auth

import com.example.prueba_tecnica_popular.data.api.ApiResult
import com.example.prueba_tecnica_popular.data.auth.AuthRepository
import com.example.prueba_tecnica_popular.data.auth.model.LoginModel
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): ApiResult<LoginModel> =
        repository.login(email, password)
}
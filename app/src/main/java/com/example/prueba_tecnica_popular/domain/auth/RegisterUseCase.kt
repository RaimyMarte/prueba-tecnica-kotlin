package com.example.prueba_tecnica_popular.domain.auth

import com.example.prueba_tecnica_popular.data.api.ApiResult
import com.example.prueba_tecnica_popular.data.auth.AuthRepository
import com.example.prueba_tecnica_popular.data.auth.model.RegisterModel
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): ApiResult<RegisterModel> =
        repository.register(email, password)
}
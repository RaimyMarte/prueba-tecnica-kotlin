package com.example.prueba_tecnica_popular.domain.auth

import com.example.prueba_tecnica_popular.data.auth.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke() = repository.logout()
}
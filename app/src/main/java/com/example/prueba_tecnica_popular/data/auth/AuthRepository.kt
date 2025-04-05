package com.example.prueba_tecnica_popular.data.auth

import com.example.prueba_tecnica_popular.core.SessionManager
import com.example.prueba_tecnica_popular.data.api.ApiResult
import com.example.prueba_tecnica_popular.data.auth.model.LoginModel
import com.example.prueba_tecnica_popular.data.auth.model.RegisterModel
import com.example.prueba_tecnica_popular.data.auth.network.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthService,
    private val sessionManager: SessionManager
) {
    suspend fun login(email: String, password: String): ApiResult<LoginModel> {
        val response = api.login(email, password)

        if (response is ApiResult.Success) {
            val token = response.data.token

            if (token.isNotEmpty()) {
                sessionManager.saveToken(token)
            }
        }

        return response
    }

    suspend fun register(email: String, password: String): ApiResult<RegisterModel> {
        return api.register(email, password)
    }

    suspend fun logout() {
        sessionManager.clearToken()
    }

    suspend fun getToken(): String? {
       return sessionManager.getToken()
    }
}
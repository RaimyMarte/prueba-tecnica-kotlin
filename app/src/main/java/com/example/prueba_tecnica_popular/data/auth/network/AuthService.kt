package com.example.prueba_tecnica_popular.data.auth.network

import com.example.prueba_tecnica_popular.core.ApiResponseHandler
import com.example.prueba_tecnica_popular.data.api.ApiResult
import com.example.prueba_tecnica_popular.data.auth.model.LoginModel
import com.example.prueba_tecnica_popular.data.auth.model.LoginRequestBody
import com.example.prueba_tecnica_popular.data.auth.model.RegisterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthService @Inject constructor(private val api: AuthApiClient) {
    suspend fun login(email: String, password: String): ApiResult<LoginModel> {
        return withContext(Dispatchers.IO) {
            val response = api.login(LoginRequestBody(email, password))

            ApiResponseHandler.handleResponse(response)
        }
    }

    suspend fun register(email: String, password: String): ApiResult<RegisterModel> {
        return withContext(Dispatchers.IO) {
            val response = api.register(LoginRequestBody(email, password))

            ApiResponseHandler.handleResponse(response)
        }
    }
}
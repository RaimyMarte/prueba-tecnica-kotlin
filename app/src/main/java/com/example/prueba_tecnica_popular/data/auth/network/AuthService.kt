package com.example.prueba_tecnica_popular.data.auth.network

import com.example.prueba_tecnica_popular.data.auth.model.LoginModel
import com.example.prueba_tecnica_popular.data.auth.model.LoginRequestBody
import com.example.prueba_tecnica_popular.data.auth.model.RegisterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthService @Inject constructor(private val api: AuthApiClient) {
    suspend fun login(email: String, password: String): LoginModel? {
        return withContext(Dispatchers.IO) {
            val response = api.login(LoginRequestBody(email, password))

            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }

    suspend fun register(email: String, password: String): RegisterModel? {
        return withContext(Dispatchers.IO) {
            val response = api.register(LoginRequestBody(email, password))

            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }
}
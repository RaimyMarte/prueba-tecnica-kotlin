package com.example.prueba_tecnica_popular.data.auth

import com.example.prueba_tecnica_popular.data.auth.model.LoginModel
import com.example.prueba_tecnica_popular.data.auth.model.RegisterModel
import com.example.prueba_tecnica_popular.data.auth.network.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: AuthService){
    suspend fun login(email: String, password: String): LoginModel? {
        return api.login(email, password)
    }

    suspend fun register(email: String, password: String): RegisterModel? {
        return api.register(email, password)
    }
}
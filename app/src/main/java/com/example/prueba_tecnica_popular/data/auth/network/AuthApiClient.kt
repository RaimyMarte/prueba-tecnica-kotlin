package com.example.prueba_tecnica_popular.data.auth.network

import com.example.prueba_tecnica_popular.data.auth.model.LoginModel
import com.example.prueba_tecnica_popular.data.auth.model.LoginRequestBody
import com.example.prueba_tecnica_popular.data.auth.model.RegisterModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiClient {
    @POST ("/api/login")
    suspend fun login(@Body request: LoginRequestBody): Response<LoginModel>

    @POST ("/api/register")
    suspend fun register(@Body request: LoginRequestBody): Response<RegisterModel>
}
package com.example.prueba_tecnica_popular.data.user.network

import com.example.prueba_tecnica_popular.data.user.model.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface UserApiClient {
    @GET("/users")
    suspend fun getAllUsers(): Response<List<UserModel>>
}
package com.example.prueba_tecnica_popular.data.user.network

import com.example.prueba_tecnica_popular.data.user.model.UserApiResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiClient {
    @GET("/api/users")

    suspend fun getAllUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): Response<UserApiResponseModel>
}

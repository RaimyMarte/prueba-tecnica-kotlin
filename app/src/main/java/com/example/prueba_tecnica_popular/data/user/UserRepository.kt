package com.example.prueba_tecnica_popular.data.user

import com.example.prueba_tecnica_popular.data.api.ApiResult
import com.example.prueba_tecnica_popular.data.user.model.UserApiResponseModel
import com.example.prueba_tecnica_popular.data.user.network.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: UserService) {
    suspend fun getAllUsers(page: Int): ApiResult<UserApiResponseModel> {
        return api.getUsers(page)
    }
}
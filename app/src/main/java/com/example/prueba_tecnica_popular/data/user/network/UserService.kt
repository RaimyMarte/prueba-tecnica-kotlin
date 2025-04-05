package com.example.prueba_tecnica_popular.data.user.network

import com.example.prueba_tecnica_popular.core.ApiResponseHandler
import com.example.prueba_tecnica_popular.data.api.ApiResult
import com.example.prueba_tecnica_popular.data.user.model.UserApiResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(private val api: UserApiClient) {
    suspend fun getUsers(page: Int): ApiResult<UserApiResponseModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllUsers(page)

            ApiResponseHandler.handleResponse(response)
        }
    }
}
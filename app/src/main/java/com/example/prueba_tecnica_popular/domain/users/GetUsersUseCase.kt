package com.example.prueba_tecnica_popular.domain.users

import com.example.prueba_tecnica_popular.data.api.ApiResult
import com.example.prueba_tecnica_popular.data.user.UserRepository
import com.example.prueba_tecnica_popular.data.user.model.UserApiResponseModel
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(page: Int): ApiResult<UserApiResponseModel> =
        repository.getAllUsers(page)
}
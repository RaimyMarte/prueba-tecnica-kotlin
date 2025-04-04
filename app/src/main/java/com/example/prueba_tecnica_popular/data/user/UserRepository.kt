package com.example.prueba_tecnica_popular.data.user

import com.example.prueba_tecnica_popular.data.user.model.UserModel
import com.example.prueba_tecnica_popular.data.user.network.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: UserService){
    suspend fun getAllUsers (): List<UserModel>{
        val response = api.getUsers()
        return response
    }
}
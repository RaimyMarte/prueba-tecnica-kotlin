package com.example.prueba_tecnica_popular.core.navigation

import com.example.prueba_tecnica_popular.data.user.model.UserModel
import kotlinx.serialization.Serializable

@Serializable
object  Login

@Serializable
object  UsersList

@Serializable
data class  UserDetails (val user: String)
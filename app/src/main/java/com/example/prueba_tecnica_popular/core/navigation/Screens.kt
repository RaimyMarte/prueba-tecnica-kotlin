package com.example.prueba_tecnica_popular.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object  Login

@Serializable
object  SignUp

@Serializable
object  UsersList

@Serializable
data class  UserDetails (val user: String)
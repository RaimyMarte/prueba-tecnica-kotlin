package com.example.prueba_tecnica_popular.data.auth.model

import com.google.gson.annotations.SerializedName

data class LoginRequestBody(
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "password") val password: String
)

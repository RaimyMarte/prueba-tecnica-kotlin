package com.example.prueba_tecnica_popular.data.auth.model

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName(value = "token") val token: String
)
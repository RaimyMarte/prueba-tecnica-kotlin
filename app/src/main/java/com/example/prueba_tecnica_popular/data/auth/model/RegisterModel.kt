package com.example.prueba_tecnica_popular.data.auth.model

import com.google.gson.annotations.SerializedName

data class RegisterModel(
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "token") val token: String
)
package com.example.prueba_tecnica_popular.ui.auth.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
   @SerializedName(value = "id") val id: Int,
   @SerializedName(value = "email") val email: String,
   @SerializedName(value = "first_name") val firstName: String,
   @SerializedName(value = "last_name") val lastName: String,
  // @SerializedName(value = "avatar") val avatar: String,
)
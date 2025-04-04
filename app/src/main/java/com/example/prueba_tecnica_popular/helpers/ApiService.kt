package com.example.prueba_tecnica_popular.helpers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    fun getRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
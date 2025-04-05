package com.example.prueba_tecnica_popular.di

import com.example.prueba_tecnica_popular.data.auth.network.AuthApiClient
import com.example.prueba_tecnica_popular.data.user.network.UserApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideUserApiClient(retrofit: Retrofit): UserApiClient{
        return retrofit.create(UserApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthApiClient(retrofit: Retrofit): AuthApiClient{
        return retrofit.create(AuthApiClient::class.java)
    }
}
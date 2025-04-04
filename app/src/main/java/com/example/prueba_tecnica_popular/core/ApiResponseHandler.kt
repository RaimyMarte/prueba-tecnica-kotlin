package com.example.prueba_tecnica_popular.core

import com.example.prueba_tecnica_popular.data.api.ApiResult
import org.json.JSONObject
import retrofit2.Response

object ApiResponseHandler {
    fun <T> handleResponse(response: Response<T>): ApiResult<T> {
        return if (response.isSuccessful) {
            response.body()?.let {
                ApiResult.Success(it)
            } ?: ApiResult.Error("Empty response body")
        } else {
            val errorMsg = try {
                val errorJson = response.errorBody()?.string()
                JSONObject(errorJson ?: "").getString("error")
            } catch (e: Exception) {
                "Unexpected error"
            }

            ApiResult.Error(errorMsg)
        }
    }
}
package com.example.shopbook.data.api


import com.example.shopbook.data.model.LoginRequest
import com.example.shopbook.data.model.LoginResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
        @POST("login")
        suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}



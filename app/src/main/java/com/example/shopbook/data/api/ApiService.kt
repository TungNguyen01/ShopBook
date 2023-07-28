package com.example.shopbook.data.api

import com.example.shopbook.data.model.AccessTokenResponse
import com.example.shopbook.data.model.ForgotResponse
import com.example.shopbook.data.model.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("customers/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>
    @FormUrlEncoded
    @POST("customers")
    fun register(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("password") password: String,
    ): Call<AccessTokenResponse>
    @FormUrlEncoded
    @POST("customers/forgotPass")
    suspend fun forgot(
        @Field("email") email : String,
    ): Response<ForgotResponse>
}



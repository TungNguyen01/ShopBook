package com.example.shopbook.data.api

import android.content.Context
import android.util.Log
import com.example.shopbook.utils.MySharedPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dfd9-116-97-243-66.ngrok-free.app/"
//    val accessToken=MySharedPreferences.getAccessToken(null)
    private var accessToken=""

    fun updateAccessToken(token: String) {
        accessToken = token
    }
    private val retrofit: Retrofit by lazy {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("user-key", accessToken)
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        })

        val client = httpClient.build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val apiService: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}
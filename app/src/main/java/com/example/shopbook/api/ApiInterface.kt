package com.example.shopbook.api

import com.example.shopbook.data.model.ProductList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    suspend fun getProducts(): Response<ProductList>
}
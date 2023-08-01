package com.example.shopbook.api

import com.example.shopbook.data.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    suspend fun getProducts(): Response<ProductList>
    @GET("products/hot")
    suspend fun getHotBook(): Response<HotBookList>
    @GET("category")
    suspend fun getCategory(): Response<CategoryList>

    @GET("products/new")
    suspend fun getNewBook(): Response<NewArrivalList>

    @GET("author/hot")
    suspend fun getAuthor(): Response<AuthorList>
}
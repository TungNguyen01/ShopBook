package com.example.shopbook.data.repository.search

import com.example.shopbook.data.model.Product
import com.example.shopbook.data.model.ProductList
import retrofit2.Response

interface SearchRepository {
    suspend fun getAllProducts(): Response<ProductList>?
}
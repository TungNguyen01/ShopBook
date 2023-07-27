package com.example.shopbook.datasource

import com.example.shopbook.data.model.ProductList
import retrofit2.Response

interface IDataSource {
    suspend fun getProducts(): Response<ProductList>?
}
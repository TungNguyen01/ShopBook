package com.example.shopbook.data.repository.product

import com.example.shopbook.data.model.ProductInfoList
import com.example.shopbook.data.model.ProductList
import com.example.shopbook.data.model.ProductsByAuthor
import retrofit2.Response

interface ProductRepository {
    suspend fun getProductInfo(id: Int): Response<ProductInfoList>?
    suspend fun getProductsByAuthor(
        author_id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductsByAuthor>?
    suspend fun getProductsByCategory(
        author_id: Int,
        limit: Int,
        page: Int,
        description_length: Int,
    ): Response<ProductList>?
}
package com.example.shopbook.data.repository.search

import com.example.shopbook.data.model.Product
import com.example.shopbook.data.model.ProductList
import com.example.shopbook.data.model.ProductNewList
import retrofit2.Response

interface SearchRepository {
    suspend fun getSearchProducts(
        limit: Int,
        page: Int,
        descriptionLength: Int,
        queryString: String,
        filterType: Int,
        priceSortOrder: String,
    ): Response<ProductList>?

    suspend fun getSearchAuthorProducts(
        authorId: Int,
        limit: Int,
        page: Int,
        descriptionLength: Int,
        queryString: String,
    ): Response<ProductList>?

    suspend fun getSearchNewProduct(): Response<ProductNewList>?
}
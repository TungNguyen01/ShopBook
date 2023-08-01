package com.example.shopbook.datasource

import com.example.shopbook.data.model.*
import retrofit2.Response

interface IDataSource {
    suspend fun getProducts(): Response<ProductList>?
    suspend fun getBooks(): Response<HotBookList>?

    suspend fun getCategory() : Response<CategoryList>?

    suspend fun getNewBook() : Response<NewArrivalList>?

    suspend fun getAuthor() : Response<AuthorList>?
}
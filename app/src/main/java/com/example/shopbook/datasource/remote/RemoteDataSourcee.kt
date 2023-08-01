package com.example.shopbook.datasource.remote

import com.example.shopbook.api.RetrofitClient
import com.example.shopbook.data.model.*
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class RemoteDataSource() : IDataSource {

    override suspend fun getProducts(): Response<ProductList>? {
        return RetrofitClient.getRetrofitClient()?.getProducts()
    }

    override suspend fun getBooks(): Response<HotBookList>? {
        return RetrofitClient.getRetrofitClient()?.getHotBook()
    }

    override suspend fun getCategory(): Response<CategoryList>? {
        return RetrofitClient.getRetrofitClient()?.getCategory()
    }

    override suspend fun getNewBook(): Response<NewArrivalList>? {
        return RetrofitClient.getRetrofitClient()?.getNewBook()
    }

    override suspend fun getAuthor(): Response<AuthorList>? {
        return RetrofitClient.getRetrofitClient()?.getAuthor()
    }
}
package com.example.shopbook.datasource.remote

import com.example.shopbook.api.RetrofitClient
import com.example.shopbook.data.model.ProductList
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class RemoteDataSource() : IDataSource {

    override suspend fun getProducts(): Response<ProductList>? {
        return RetrofitClient.getRetrofitClient()?.getProducts()
    }
}
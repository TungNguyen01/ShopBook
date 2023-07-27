package com.example.shopbook.data.repository.search

import com.example.shopbook.data.model.ProductList
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class SearchRepositoryImp(private val dataSource: IDataSource) : SearchRepository {
    override suspend fun getAllProducts(): Response<ProductList>? {
        return dataSource.getProducts()
    }
}
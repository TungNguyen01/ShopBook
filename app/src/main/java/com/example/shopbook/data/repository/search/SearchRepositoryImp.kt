package com.example.shopbook.data.repository.search

import com.example.shopbook.data.model.ProductList
import com.example.shopbook.data.model.ProductNewList
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class SearchRepositoryImp(private val dataSource: IDataSource) : SearchRepository {
    override suspend fun getSearchProducts(
        limit: Int,
        page: Int,
        descriptionLength: Int,
        queryString: String,
        filterType: Int,
        priceSortOrder: String,
    ): Response<ProductList>? {
        return dataSource.getSearchProducts(
            limit,
            page,
            descriptionLength,
            queryString,
            filterType,
            priceSortOrder,
        )
    }

    override suspend fun getSearchAuthorProducts(
        authorId: Int,
        limit: Int,
        page: Int,
        descriptionLength: Int,
        queryString: String,
    ): Response<ProductList>? {
        return dataSource.getSearchAuthorProducts(
            authorId,
            limit,
            page,
            descriptionLength,
            queryString,
        )
    }

    override suspend fun getSearchNewProduct(): Response<ProductNewList>? {
        return dataSource.getSearchNewProduct()
    }
}
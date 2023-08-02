package com.example.shopbook.data.repository.product

import com.example.shopbook.data.model.ProductInfoList
import com.example.shopbook.data.model.ProductList
import com.example.shopbook.data.model.ProductsByAuthor
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class ProductRepositoryImp(private val dataSource: IDataSource) : ProductRepository {
    override suspend fun getProductInfo(id: Int): Response<ProductInfoList>? {
        return dataSource.getProductInfo(id)
    }

    override suspend fun getProductsByAuthor(
        author_id: Int,
        limit: Int,
        description_length: Int,
    ): Response<ProductsByAuthor>? {
        return dataSource.getProductsByAuthor(author_id, limit, description_length)
    }
}
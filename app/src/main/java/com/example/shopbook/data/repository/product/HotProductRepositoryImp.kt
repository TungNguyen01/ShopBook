package com.example.shopbook.data.repository.product

import com.example.shopbook.data.model.HotBookList
import com.example.shopbook.data.model.ProductInfoList
import com.example.shopbook.data.repository.hotbook.HotBookRepository
import com.example.shopbook.data.repository.hotbook.HotBookRepositoryImp
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class HotProductRepositoryImp(private val dataSource: IDataSource): HotProductRepository {
    override suspend fun getProductInfo(id: Int): Response<ProductInfoList>? {
        return dataSource.getProductInfo(id)
    }
}
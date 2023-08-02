package com.example.shopbook.data.repository.product

import com.example.shopbook.data.model.ProductInfoList
import retrofit2.Response

interface HotProductRepository {
    suspend fun getProductInfo(id: Int): Response<ProductInfoList>?
}
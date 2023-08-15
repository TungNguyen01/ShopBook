package com.example.shopbook.data.repository.supply

import com.example.shopbook.data.model.ProductList
import com.example.shopbook.data.model.SupplyList
import retrofit2.Response

interface SupplyRepository {
    suspend fun getSupply(id: Int, limit: Int, page: Int, description_length: Int) : Response<ProductList>?
    suspend fun getSearchSupply(
        supplyId: Int,
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
    ): Response<ProductList>?
}
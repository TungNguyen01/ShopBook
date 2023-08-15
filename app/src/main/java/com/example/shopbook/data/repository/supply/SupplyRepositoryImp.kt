package com.example.shopbook.data.repository.supply
import com.example.shopbook.data.api.RetrofitClient
import com.example.shopbook.data.model.ProductList
import com.example.shopbook.data.model.SupplyList
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class SupplyRepositoryImp(private val dataSource: IDataSource) : SupplyRepository {
    override suspend fun getSupply(
        id: Int,
        limit: Int,
        page: Int,
        description_length: Int
    ): Response<ProductList>? {
        return dataSource.getSuppy(id, limit, page, description_length)
    }

    override suspend fun getSearchSupply(
        supplyId: Int,
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String
    ): Response<ProductList>? {
        return dataSource.getSearchSupply(supplyId,limit,page,description_length,query_string)
    }
}
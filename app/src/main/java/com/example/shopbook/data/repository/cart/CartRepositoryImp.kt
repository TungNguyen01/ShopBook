package com.example.shopbook.data.repository.cart

import com.example.shopbook.data.model.CartItem
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class CartRepositoryImp(private val dataSource: IDataSource) :CartRepository {
    override suspend fun addCartItem(productId: Int): Response<List<CartItem>>? {
        return dataSource.addCartItem(productId)
    }
}
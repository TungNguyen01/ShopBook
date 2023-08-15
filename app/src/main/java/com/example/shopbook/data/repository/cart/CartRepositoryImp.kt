package com.example.shopbook.data.repository.cart

import com.example.shopbook.data.model.CartItem
import com.example.shopbook.data.model.CartItemBag
import com.example.shopbook.data.model.CartList
import com.example.shopbook.data.model.Messeage
import com.example.shopbook.datasource.IDataSource
import retrofit2.Call
import retrofit2.Response


class CartRepositoryImp(private val dataSource: IDataSource) : CartRepository {
    override suspend fun addCartItem(productId: Int): Response<List<CartItem>>? {
        return dataSource.addCartItem(productId)
    }

    override suspend fun getCart(): Response<CartList>? {
        return dataSource.getCart()
    }

    override suspend fun removeProduct(itemId: Int): Response<Messeage>? {
        return dataSource.removeProduct(itemId)
    }

    override suspend fun updateQuantity(itemId: Int, quantity: Int): Response<Messeage>? {
        return dataSource.updateQuantity(itemId, quantity)
    }
    override suspend fun addAllWishList(): Response<Messeage>? {
        return dataSource.addAllWishList()
    }
}
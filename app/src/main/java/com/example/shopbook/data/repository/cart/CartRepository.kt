package com.example.shopbook.data.repository.cart

import com.example.shopbook.data.model.CartItem
import com.example.shopbook.data.model.CartItemBag
import com.example.shopbook.data.model.CartList
import com.example.shopbook.data.model.Messeage
import retrofit2.Call
import retrofit2.Response

interface CartRepository {
    suspend fun addCartItem(productId: Int): Response<List<CartItem>>?
    suspend fun getCart() : Response<CartList>?
    suspend fun removeProduct(itemId : Int) : Response<Messeage>?

    suspend fun updateQuantity(itemId: Int, quantity : Int) : Response<Messeage>?
    suspend fun addAllWishList() : Response<Messeage>?
}
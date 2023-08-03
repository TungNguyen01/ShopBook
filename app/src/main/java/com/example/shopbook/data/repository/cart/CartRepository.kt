package com.example.shopbook.data.repository.cart

import com.example.shopbook.data.model.CartItem
import retrofit2.Response

interface CartRepository {
    suspend fun addCartItem(productId: Int): Response<List<CartItem>>?
}
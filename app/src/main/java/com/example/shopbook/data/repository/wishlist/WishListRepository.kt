package com.example.shopbook.data.repository.wishlist

import com.example.shopbook.data.model.Messeage
import com.example.shopbook.data.model.WishlistList
import retrofit2.Response

interface WishListRepository {
    suspend fun addItemToWishList(productId: Int): Response<Messeage>?
    suspend fun removeItemInWishList(productId: Int): Response<Messeage>?

    suspend fun getWishlist() : Response<WishlistList>?
}
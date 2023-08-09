package com.example.shopbook.data.repository.wishlist

import com.example.shopbook.data.model.Messeage
import com.example.shopbook.data.model.WishlistList
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class WishListRepositoryImp(private val dataSource: IDataSource) : WishListRepository {
    override suspend fun addItemToWishList(productId: Int): Response<Messeage>? {
        return dataSource.addItemToWishList(productId)
    }

    override suspend fun removeItemInWishList(productId: Int): Response<Messeage>? {
        return dataSource.removeItemInWishList(productId)
    }

    override suspend fun getWishlist(): Response<WishlistList>? {
        return dataSource.getWishlist()
    }
}
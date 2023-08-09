package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class WishlistList(
    @SerializedName("count") val count : Int,
    @SerializedName("rows") val wishlist: List<Wishlist>,
)

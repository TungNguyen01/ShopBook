package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

class ProductInfo(
    @SerializedName("product_id")
    var productId: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("price")
    var price: String,
    @SerializedName("discounted_price")
    var discountedPrice: String,
    @SerializedName("thumbnail")
    var thumbnail: String,
    @SerializedName("author_avatar")
    var authorAvatar: String,
    @SerializedName("wishlist")
    var wishlist: Int,
)
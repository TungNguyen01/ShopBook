package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class CartList(
    @SerializedName("cart_id")
    val cart_id: String,
    @SerializedName("products")
    val products: List<CartItemBag>,
)

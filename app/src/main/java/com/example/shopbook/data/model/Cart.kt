package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("cart_id")
    val cart_id: Int,
    @SerializedName("product")
    val product: Product,
)

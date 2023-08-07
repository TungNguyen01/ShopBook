package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class CartItemBag(
    @SerializedName("item_id")
    val item_id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("product_id")
    val product_id: Int,
    @SerializedName("sub_total")
    val sub_total: String,
    @SerializedName("added_on")
    val added_on: String,
    @SerializedName("discounted_price")
    val discounted_price: String,
    @SerializedName("wishlist")
    val wishlist: Int,
    @SerializedName("image")
    val image : String,
)

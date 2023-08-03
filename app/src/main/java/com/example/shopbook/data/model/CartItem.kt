package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class CartItem(
    @SerializedName("item_id") var itemId: Int,
    @SerializedName("name") var name: String,
    @SerializedName("price") var price: String,
    @SerializedName("quantity") var quantity: Int,
    @SerializedName("product_id") var productId: Int,
    @SerializedName("sub_total") var subTotal: String,
    @SerializedName("added_on") var addedOn: String,
    @SerializedName("discounted_price") var discountedPrice: String,
)

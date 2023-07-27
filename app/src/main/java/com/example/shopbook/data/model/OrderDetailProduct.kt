package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class OrderDetailProduct(
    @SerializedName("order_id") var orderId: Int? = null,
    @SerializedName("product_id") var productId: Int? = null,
    @SerializedName("product_name") var productName: String? = null,
    @SerializedName("quantity") var quantity: Int? = null,
    @SerializedName("unit_cost") var unitCost: String? = null,
    @SerializedName("subtotal") var subtotal: String? = null,
)
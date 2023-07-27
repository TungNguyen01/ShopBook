package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class Ship(
    @SerializedName("shipping_id")
    val shipping_id: Int,
    @SerializedName("shipping_type")
    val shipping_type: String,
    @SerializedName("shipping_cost")
    val shipping_cost: String,
)

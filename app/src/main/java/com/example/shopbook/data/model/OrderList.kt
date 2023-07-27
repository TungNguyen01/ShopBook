package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class OrderList(
    @SerializedName("count") var count: Int?,
    @SerializedName("orders") var orders: List<Order>,
)

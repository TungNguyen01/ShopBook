package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("count")
    var count: Int?,
    @SerializedName("rows")
    var products: List<Product>,
)
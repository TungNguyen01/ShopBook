package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class ProductsByAuthor(
    @SerializedName("count") var count: Int,
    @SerializedName("rows") var products: List<Product>,
)

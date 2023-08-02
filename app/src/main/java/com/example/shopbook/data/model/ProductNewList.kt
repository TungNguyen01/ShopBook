package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class ProductNewList(
    @SerializedName("count") var count: Int,
    @SerializedName("products") var productsNew: List<ProductNew>,
)

package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class HotBook(
    @SerializedName("product_id")
    val product_id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("price")
    val price : String,
    @SerializedName("discounted_price")
    val discounted_price : String,
    @SerializedName("thumbnail")
    val thumbnail : String
)

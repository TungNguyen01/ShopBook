package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class HotBookList(
    @SerializedName("count")
    var count: Int?,
    @SerializedName("products")
    var hotBook: List<HotBook>,
)

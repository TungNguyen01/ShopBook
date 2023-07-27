package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class CategoryList(
    @SerializedName("count") var count: Int?,
    @SerializedName("rows")
    var categories: List<Category>,
)

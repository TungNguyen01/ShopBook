package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category_id") var categoryId: Int?,
    @SerializedName("name") var name: String?,
    @SerializedName("description") var description: String?,
)

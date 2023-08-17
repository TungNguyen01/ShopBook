package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class BannerList(
    @SerializedName("count") var count: Int,
    @SerializedName("products") var products: List<Banner>,
)

package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class ProductInfoList(
    @SerializedName("product") var product: ProductInfo,
    @SerializedName("supplier") var supplier: Supplier,
    @SerializedName("author") var author: Author,
)

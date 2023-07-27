package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class ProductInfoList(
    @SerializedName("product") var product: Product?,
    @SerializedName("supplier") var supplier: Supplier?,
    @SerializedName("author") var author: Author?,
)
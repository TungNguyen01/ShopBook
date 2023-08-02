package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class Supplier(
    @SerializedName("supplier_id")
    var supplier_id: Int,
    @SerializedName("suppler_name")
    var supplier_name: String,
)

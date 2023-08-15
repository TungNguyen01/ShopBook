package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class SupplyList(
    @SerializedName("count")
    var count : Int,
    @SerializedName("rows")
    var suplly : List<Supply>,
)

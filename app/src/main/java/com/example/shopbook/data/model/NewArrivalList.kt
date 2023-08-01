package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class NewArrivalList(
    @SerializedName("count")
    val count : Int?,
    @SerializedName("products")
    val newBook : List<NewArrival>
)

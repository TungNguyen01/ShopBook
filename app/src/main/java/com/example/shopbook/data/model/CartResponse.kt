package com.example.shopbook.data.model

data class CartResponse(
    val item_id : Int,
    val name : String,
    val price : String,
    val quantity : Int,
    val product_id : Int,
    val sub_total : String,
    val added_on : String,
    val discounted_price : String
)

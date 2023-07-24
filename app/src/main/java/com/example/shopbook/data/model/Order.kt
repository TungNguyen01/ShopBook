package com.example.shopbook.data.model

data class Order(
    val order_id: Int,
    val merchandise_subtotal: String,
    val total_quantity: String,
    val created_on: String,
    val shipped_on: String,
    val comments: String,
    val customer_id: Int,
    val auth_code: String,
    val address: String,
    val receiver_name: String,
    val receiver_phone: String,
    val reference: String,
    val shipping_id: Int,
    val shipping_type: String,
)

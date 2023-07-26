package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName


data class Customer(
    @SerializedName("customer_id") val customerId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("address") val address: String?,
    @SerializedName("shipping_region_id") val shippingRegionId: Int,
    @SerializedName("mob_phone") val mobPhone: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("date_of_birth") val dateOfBirth: String?,
    @SerializedName("avatar") val avatar: String
)


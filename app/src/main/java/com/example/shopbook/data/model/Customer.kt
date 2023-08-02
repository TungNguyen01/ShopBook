package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName


data class Customer(
    @SerializedName("customer_id")
    val customer_id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("shipping_region_id")
    val shipping_region_id: Int?,
    @SerializedName("mob_phone")
    val mob_phone: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("date_of_birth")
    val date_of_birth: String?,
    @SerializedName("avatar")
    val avatar: String?

)


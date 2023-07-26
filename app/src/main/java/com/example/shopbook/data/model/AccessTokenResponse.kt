package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
    val accessToken: String,
    val customer: Customer,
    val expiresIn: String
)

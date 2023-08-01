package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class DiscoverStore(
    @SerializedName("author_id")
    var author_id: Int,
    @SerializedName("author_name")
    var author_name: String,
    @SerializedName("author_description")
    var author_des: String,
    @SerializedName("author_avatar")
    var author_avatar: String
)

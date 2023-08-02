package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("author_id") var authorId: Int,
    @SerializedName("author_name") var authorName: String,
    @SerializedName("author_description") var authorDescription: String,
    @SerializedName("author_avatar") var authorAvatar: String,
)

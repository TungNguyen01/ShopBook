package com.example.shopbook.data.model

import com.google.gson.annotations.SerializedName

data class AuthorList(
    @SerializedName("authors")
    var authors: List<Author>,
)
